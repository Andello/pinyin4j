package com.github.houbb.pinyin.spi;

import java.util.List;
import java.util.Set;

/**
 * 拼音连接接口
 *
 * （1）可以使用模式来制定对应的映射实现。
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IPinyinTone {

    /**
     * 获取映射后的信息
     * @param segment 分词后的片段
     * @return 结果
     * @since 0.0.1
     */
    String tone(final String segment);

    /**
     * 返回所有的列表
     * @param chinese 单个中文
     * @return 结果
     */
    List<String> toneList(final String chinese);

    /**
     * 词组列表
     *
     * 作用：快速分词。
     * @return 列表
     * @since 0.0.5
     */
    Set<String> phraseSet();

    /**
     * 根据中文获取对应的拼音声调
     *
     * 1. 这里暂时没有考虑多音字的问题。
     * 2. 如果考虑内容不是单个字，那么结果应该变为一个列表。
     * @param chinese 中文
     * @return 声调
     * @since 0.1.0
     */
    int toneNum(final String chinese);

}
