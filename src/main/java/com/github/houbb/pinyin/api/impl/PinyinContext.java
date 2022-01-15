package com.github.houbb.pinyin.api.impl;

import com.github.houbb.pinyin.api.IPinyinContext;
import com.github.houbb.pinyin.spi.*;

/**
 * 拼音核心用户 api
 * @author binbin.hou
 * @since 0.1.1
 */
public class PinyinContext implements IPinyinContext {

    /**
     * 格式
     * @since 0.1.1
     */
    private IPinyinToneStyle style;

    /**
     * 分词实现
     * @since 0.1.1
     */
    private IPinyinSegment segment;

    /**
     * 拼音数据实现
     * @since 0.1.1
     */
    private IPinyinData data;

    /**
     * 中文服务类
     * @since 0.0.1
     */
    private IPinyinChinese chinese;

    /**
     * 注音实现
     * @since 0.0.1
     */
    private IPinyinTone tone;

    /**
     * 连接符
     * @since 0.1.2
     */
    private String connector;

    /**
     * 拼音反向
     * @since 0.3.0
     */
    private IPinyinToneReverse pinyinToneReverse;

    /**
     * 返回实例
     * @since 0.1.1
     * @return 结果
     */
    public static PinyinContext newInstance() {
        return new PinyinContext();
    }

    @Override
    public IPinyinToneStyle style() {
        return style;
    }

    public PinyinContext style(IPinyinToneStyle style) {
        this.style = style;
        return this;
    }

    @Override
    public IPinyinSegment segment() {
        return segment;
    }

    public PinyinContext segment(IPinyinSegment segment) {
        this.segment = segment;
        return this;
    }

    @Override
    public IPinyinData data() {
        return data;
    }

    public PinyinContext data(IPinyinData data) {
        this.data = data;
        return this;
    }

    @Override
    public IPinyinChinese chinese() {
        return chinese;
    }

    public PinyinContext chinese(IPinyinChinese chinese) {
        this.chinese = chinese;
        return this;
    }

    @Override
    public IPinyinTone tone() {
        return tone;
    }

    public PinyinContext tone(IPinyinTone tone) {
        this.tone = tone;
        return this;
    }

    @Override
    public String connector() {
        return connector;
    }

    public PinyinContext connector(String connector) {
        this.connector = connector;
        return this;
    }

    @Override
    public IPinyinToneReverse pinyinToneReverse() {
        return pinyinToneReverse;
    }

    public PinyinContext pinyinToneReverse(IPinyinToneReverse pinyinToneReverse) {
        this.pinyinToneReverse = pinyinToneReverse;
        return this;
    }
}
