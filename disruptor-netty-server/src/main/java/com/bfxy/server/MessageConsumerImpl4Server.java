package com.bfxy.server;

import com.bfxy.disruptor.MessageConsumer;
import com.bfxy.entity.TranslatorData;
import com.bfxy.entity.TranslatorDataWrapper;
import io.netty.channel.ChannelHandlerContext;

/**
 * 队列的好处就在于业务逻辑处理可以放在这里异步处理
 */
public class MessageConsumerImpl4Server extends MessageConsumer {

    public MessageConsumerImpl4Server(String consumerId) {
        super(consumerId);
    }

    @Override
    public void onEvent(TranslatorDataWrapper event) {
        TranslatorData request = event.getData();
        ChannelHandlerContext ctx = event.getCtx();
        //1.业务处理逻辑:
        System.err.println("Sever端:" + request);

        //2.回送响应信息:
        TranslatorData response = new TranslatorData();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setMessage(request.getMessage());
        //写出response响应信息:
        ctx.writeAndFlush(response);
    }

}
