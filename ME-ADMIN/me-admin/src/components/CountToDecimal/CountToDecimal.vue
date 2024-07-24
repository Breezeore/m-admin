<template>
  <span :style="{ color }">
    {{ value }}
  </span>
</template>
<script lang="ts">
  import { defineComponent, ref, computed, watchEffect, unref, onMounted, watch } from 'vue';
  import { useTransition, TransitionPresets } from '@vueuse/core';
  import { isNumber } from '@/utils/is';

  const props = {
    startVal: { type: Number, default: 0 },
    endVal: { type: Number, default: 0 },
    duration: { type: Number, default: 1000 },
    autoplay: { type: Boolean, default: true },
    decimals: {
      type: Number,
      default: 0,
      validator(value: number) {
        return value >= 0;
      },
    },
    prefix: { type: String, default: '' },
    suffix: { type: String, default: '' },
    separator: { type: String, default: ',' },
    decimal: { type: String, default: '.' },
    /**
     * font color
     */
    color: { type: String },
    /**
     * Turn on digital animation
     */
    useEasing: { type: Boolean, default: true },
    /**
     * Digital animation
     */
    transition: { type: String, default: 'linear' },
  };

  export default defineComponent({
    name: 'CountTo',
    props,
    emits: ['onStarted', 'onFinished'],
    setup(props, { emit }) {
      const source = ref(props.startVal);
      const disabled = ref(false);
      let outputValue = useTransition(source);

      const value = computed(() => formatNumber(unref(outputValue)));

      watchEffect(() => {
        source.value = props.startVal;
      });

      watch([() => props.startVal, () => props.endVal], () => {
        if (props.autoplay) {
          start();
        }
      });

      onMounted(() => {
        props.autoplay && start();
      });

      function start() {
        run();
        source.value = props.endVal;
      }

      function reset() {
        source.value = props.startVal;
        run();
      }

      function run() {
        outputValue = useTransition(source, {
          disabled,
          duration: props.duration,
          onFinished: () => emit('onFinished'),
          onStarted: () => emit('onStarted'),
          ...(props.useEasing ? { transition: TransitionPresets[props.transition] } : {}),
        });
      }

      function formatNumber(num) {
        if (!num) {
          return '';
        }
        const { separator, suffix, prefix } = props;
        let formattedNum = '';
      
        if (num % 1 === 0) {
          // 如果没有小数部分，返回整数部分
          formattedNum = String(parseInt(num));
        } else {
          // 如果有小数部分，保留两位小数
          formattedNum = num.toFixed(2);
        }
      
        const rgx = /(\d+)(\d{3})/;
        if (separator && !isNumber(separator)) {
          while (rgx.test(formattedNum)) {
            formattedNum = formattedNum.replace(rgx, '$1' + separator + '$2');
          }
        }
      
        return prefix + formattedNum + suffix;
      }


      

      return { value, start, reset };
    },
  });
</script>
