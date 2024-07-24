import store from 'store2'

/**
 * 根据key获取本队列存储的值
 * @param {*} key 
 * @returns 
 */
export function getStorage(key){
    return store.get(key)
}

/**
 * 设置本地存储 传递key和val
 * @param {*} key 
 * @param {*} val 
 * @returns 
 */
export function setStorage(key,val){
    return store.set(key,val)
}

/**
 * 删除本地存储 传递key
 * @param {*} key 
 * @returns 
 */
export function delStorage(key){
    return store.remove(key)
}
