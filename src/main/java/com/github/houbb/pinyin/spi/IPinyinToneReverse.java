package com.github.houbb.pinyin.spi;

import java.util.List;

/**
 * 拼音的反向数据
 *
 * key: pinyin
 * value: 汉字列表
 *
 * @author binbin.hou
 * @since 0.3.0
 */
public interface IPinyinToneReverse {

    /**
     * 获取对应的汉字列表
     * @param pinyinLast pinyin1 这种格式的拼音
     * @return 结果
     * @since 0.3.0
     */
    List<String> getHanziList(String pinyinLast);


}
