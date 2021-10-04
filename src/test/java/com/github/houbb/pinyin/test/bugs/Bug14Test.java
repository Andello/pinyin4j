package com.github.houbb.pinyin.test.bugs;

import com.github.houbb.pinyin.util.PinyinHelper;
import org.junit.Assert;
import org.junit.Test;

public class Bug14Test {

    @Test
    public void sanTest() {
        Assert.assertEquals("sān", PinyinHelper.toPinyin("叁"));
        Assert.assertEquals("cān", PinyinHelper.toPinyin("叄"));
        Assert.assertEquals("[sān]", PinyinHelper.toPinyinList('叁').toString());
        Assert.assertEquals("[cān]", PinyinHelper.toPinyinList('叄').toString());
    }

}
