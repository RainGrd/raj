// message.js  这是自定义的文件
import { Message } from 'element-ui'; // 引入message

let messageInstance = null;
const resetMessage = (options) => {
    if(messageInstance) {
        messageInstance.close() //关闭
    }
    messageInstance = Message(options)
};
['error','success','info','warning'].forEach(type => {
    resetMessage[type] = options => {
        if(typeof options === 'string') {
            options = {
                message:options
            }
        }
        options.type = type
        return resetMessage(options)
    }
})
export const message = resetMessage