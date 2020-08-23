package com.github.houbb.pinyin.test.util;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.1.2
 */
public class PinyinHelperConnectorTest {

    /**
     * 首字母用空字符串连接
     * @since 0.1.2
     */
    @Test
    public void firstLetterEmptyTest() {
        final String text = "我爱中文";

        Assert.assertEquals("wazw", PinyinHelper.toPinyin(text, PinyinStyleEnum.FIRST_LETTER, StringUtil.EMPTY));
    }

    @Test
    public void baseCaseTest() {
        final String text = "这个是测试";
        Assert.assertEquals("zgscs", PinyinHelper.toPinyin(text,
                PinyinStyleEnum.FIRST_LETTER, StringUtil.EMPTY));
    }

}
