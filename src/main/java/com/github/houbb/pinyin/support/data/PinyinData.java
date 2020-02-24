package com.github.houbb.pinyin.support.data;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.spi.IPinyinData;

import java.util.Arrays;
import java.util.List;

/**
 * 声母韵母
 * @author binbin.hou
 * @since 0.1.1
 */
@ThreadSafe
public class PinyinData implements IPinyinData {

    /**
     * 零声母列表
     * a ai an ang ao e ê ei en eng er o ou
     * @since 0.1.1
     */
    private static final List<String> ZERO_SHENG_MU_LIST = Arrays
            .asList("a", "ai", "an", "ang", "ao", "e", "ê", "ei", "en", "eng", "er", "o", "ou");


    /**
     * 双字母的声母
     * zh
     * ch
     * sh
     * @since 0.1.1
     */
    private static final List<String> DOUBLE_SHENG_MU_LIST = Arrays.asList("zh", "ch", "sh");

    @Override
    public String shengMu(String pinyinNormal) {
        if(isZeroShengMu(pinyinNormal)) {
            return StringUtil.EMPTY;
        }

        final String prefixDouble = pinyinNormal.substring(0, 2);
        if(DOUBLE_SHENG_MU_LIST.contains(prefixDouble)) {
            return prefixDouble;
        }

        // 默认返回第一个音节
        return pinyinNormal.substring(0, 1);
    }

    @Override
    public String yunMu(String pinyinNormal) {
        String shengMu = shengMu(pinyinNormal);
        return pinyinNormal.substring(shengMu.length());
    }

    @Override
    public boolean isZeroShengMu(String pinyinNormal) {
        return ZERO_SHENG_MU_LIST.contains(pinyinNormal);
    }

}
