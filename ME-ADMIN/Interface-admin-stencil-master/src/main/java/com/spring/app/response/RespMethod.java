package com.spring.app.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName RespMethod
 * @Date 2023/6/3 9:25
 * @Author BREEZE
 **/
@Component
public class RespMethod {
    @Autowired
    private RespData respData;

    @Autowired
    private RespDataPage respDataPage;

    //登录成功
    public RespData loginSuccess(Object data){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(Code.LOGIN_SUCCESS_HINT);
        return respData;
    }

    //登录失败
    public RespData loginError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.LOGIN_ERROR_HINT);
        return respData;
    }

    //登录失败
    public RespData loginErrorMessage (Object message){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(message);
        return respData;
    }

    /**
     * 操作成功 携带数据
     * @param data
     * @return
     */
    public RespData operateSuccessByData(Object data){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(Code.OPERATE_SUCCESS_HINT);
        return respData;
    }

    /**
     * 操作成功 携带消息
     * @param massage
     * @return
     */
    public RespData operateSuccessByMessage(Object massage){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(massage);
        return respData;
    }

    /**
     * 操作成功 携带数据和消息
     * @param data
     * @param massage
     * @return
     */
    public RespData operateSuccessByDataAndMessage(Object data,Object massage){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(massage);
        return respData;
    }

    /**
     * 操作失败
     * @return
     */
    public RespData operateError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.OPERATE_ERROR_HINT);
        return respData;
    }

    /**
     * 操作失败 携带消息
     * @param massage
     * @return
     */
    public RespData operateErrorByMessage(Object massage){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(massage);
        return respData;
    }




    //添加成功
    public RespData insertSuccess(Object data){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(Code.INSERT_SUCCESS_HINT);
        return respData;
    }

    //添加失败
    public RespData insertError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.INSERT_ERROR_HINT);
        return respData;
    }

    //修改成功
    public RespData updateSuccess(Object data){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(Code.UPDATE_SUCCESS_HINT);
        return respData;
    }

    //修改失败
    public RespData updateError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.UPDATE_ERROR_HINT);
        return respData;
    }

    //删除成功
    public RespData deleteSuccess(Object data){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(Code.DELETE_SUCCESS_HINT);
        return respData;
    }

    //删除失败
    public RespData deleteError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.DELETE_ERROR_HINT);
        return respData;
    }

    //查询成功
    public RespData inquireSuccess(Object data){
        respData.setData(data);
        respData.setCode(Code.DATA_SUCCESS);
        respData.setMessage(Code.PAGE_SUCCESS_HINT);
        return respData;
    }

    //查询成功
    public RespDataPage inquireSuccessPage(Object data,Object count){
        respDataPage.setData(data);
        respDataPage.setCode(Code.DATA_SUCCESS);
        respDataPage.setCount(count);
        respDataPage.setMessage(Code.PAGE_SUCCESS_HINT);
        return respDataPage;
    }

    //查询失败
    public RespData inquireError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.PAGE_ERROR_HINT);
        return respData;
    }

    //参数校验失败
    public RespData parameterCheck(Object massage){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(massage);
        return respData;
    }

    //身份验证失败
    public RespData identityError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.IDENTITY_ERROR);
        respData.setMessage(Code.IDENTITY_ERROR_HINT);
        return respData;
    }

    //角色权限不足
    public RespData roleError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.ROLE_ERROR_HINT);
        return respData;
    }

    //用户权限不足
    public RespData powerError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.POWER_ERROR_HINT);
        return respData;
    }

    //其他系统异常
    public RespData systemError(){
        respData.setData(Code.NULL);
        respData.setCode(Code.DATA_ERROR);
        respData.setMessage(Code.SYSTEM_ERROR_HINT);
        return respData;
    }

    //判断新增操作是否成功
    public RespData insert(int insert) {
        if (insert > 0) {
            return insertSuccess(true);
        } else {
            return insertError();
        }
    }

    //判断修改操作是否成功
    public RespData update(int update) {
        if (update > 0) {
            return updateSuccess(true);
        } else {
            return updateError();
        }
    }

    //判断删除操作是否成功
    public RespData delete(int delete) {
        if (delete > 0) {
            return deleteSuccess(true);
        } else {
            return deleteError();
        }
    }
}
