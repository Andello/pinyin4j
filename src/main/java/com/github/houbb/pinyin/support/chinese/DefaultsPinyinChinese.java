package com.github.houbb.pinyin.support.chinese;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CharsetUtil;
import com.github.houbb.nlp.common.format.impl.CharFormats;
import com.github.houbb.pinyin.spi.IPinyinChinese;

/**
 * 默认实现，不对中文进行转换处理。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class DefaultsPinyinChinese implements IPinyinChinese {

    @Override
    public boolean isChinese(String original) {
        // 分词有一个问题。如果分词结果为 A栋
        // 直接判断，并不是中文。
        // 这个后续进行处理，暂时不做细致处理。
        // 应该是包含中文，就处理。
        // 如果没有全部匹配，那么就降级为单个字的拼音处理。

        // 只要包含一个中文字符，则认为是
        if(StringUtil.isEmpty(original)) {
            return false;
        }

        char[] chars = original.toCharArray();
        for(char c : chars) {
            if(isChinese(c)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isChinese(char original) {
        return CharUtil.isNotAscii(original);
    }

    @Override
    public String toSimple(String segment) {
        return segment;
    }

    @Override
    public String toSimple(char original) {
        return original+"";
    }

}
