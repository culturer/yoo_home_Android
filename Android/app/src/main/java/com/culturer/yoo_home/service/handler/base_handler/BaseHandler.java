package com.culturer.yoo_home.service.handler.base_handler;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

/**
 * mvp模式的极简化,用于service.将复杂的处理逻辑独立出来
 * @param <S>
 */
public abstract class BaseHandler<M extends BaseHandleMsg> {
    public abstract void initHandler();
    public abstract void handle(M msg);
}
