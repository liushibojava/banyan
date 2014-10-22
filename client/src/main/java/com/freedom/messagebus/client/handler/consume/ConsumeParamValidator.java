package com.freedom.messagebus.client.handler.consume;

import com.freedom.messagebus.client.MessageContext;
import com.freedom.messagebus.client.handler.IHandlerChain;
import com.freedom.messagebus.client.handler.ParamValidateFailedException;
import com.freedom.messagebus.client.handler.common.AbstractParamValidator;
import com.freedom.messagebus.client.model.MessageCarryType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

public class ConsumeParamValidator extends AbstractParamValidator {

    private static final Log logger = LogFactory.getLog(ConsumeParamValidator.class);

    @Override
    public void handle(@NotNull MessageContext context, @NotNull IHandlerChain chain) {
        super.handle(context, chain);

        if (context.getCarryType().equals(MessageCarryType.CONSUME)) {
            if (context.getQueueNode().getValue() == null || context.getQueueNode().getValue().isEmpty()) {
                logger.error("[handle] the context field : queueNode is illegal. the value can not be null or empty");
                throw new ParamValidateFailedException("[handle] the context field : queueNode is illegal. " +
                                                           "the value can not be null or empty");
            }

            if (context.getQueueNode() == null) {
                logger.error("[handle] the context field : queueNode is illegal. it can not be null");
                throw new ParamValidateFailedException("the context field : queueNode is illegal. " +
                                                           "it can not be null ");
            }

            if (context.getQueueNode().getType() == 0) {
                logger.error("[handle] the context field : queueNode is illegal. it must be a queue type");
                throw new ParamValidateFailedException("[handle] the context field : queueNode is illegal. " +
                                                           "it must be a queue type");
            }

            if (context.getQueueNode().getName() == null || context.getQueueNode().getName().isEmpty()) {
                logger.error("[handle] the context field : queueNode is illegal. the name can not be null or empty");
                throw new ParamValidateFailedException("[handle] the context field : queueNode is illegal. " +
                                                           "the name can not be null or empty");
            }
        }

        chain.handle(context);
    }
}