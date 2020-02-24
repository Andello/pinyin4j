package com.github.houbb.pinyin.api.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.pinyin.api.IPinyin;
import com.github.houbb.pinyin.api.IPinyinContext;
import com.github.houbb.pinyin.spi.*;

import java.util.Collections;
import java.util.List;

/**
 * <p> project: pinyin-Pinyin </p>
 * <p> create on 2020/2/24 22:27 </p>
 *
 * @author Administrator
 * @since 1.0.0
 */
@ThreadSafe
public class Pinyin implements IPinyin {

    @Override
    public String toPinyin(String string, IPinyinContext context) {
        List<String> stringList = toPinyinList(string, context);
        return StringUtil.join(stringList, StringUtil.BLANK);
    }

    @Override
    public List<String> toPinyinList(char chinese, IPinyinContext context) {
        String original = String.valueOf(chinese);

        // 基本属性
        final IPinyinChinese pinyinChinese = context.chinese();
        final IPinyinTone pinyinTone = context.tone();

        if(pinyinChinese.isChinese(original)) {
            String simple = pinyinChinese.toSimple(original);
            return pinyinTone.toneList(simple, context);
        }

        // 直接返回
        return Collections.singletonList(original);
    }

    @Override
    public boolean hasSamePinyin(char chineseOne, char chineseTwo, IPinyinContext context) {
        // 基本属性
        final IPinyinChinese pinyinChinese = context.chinese();
        final IPinyinTone pinyinTone = context.tone();

        if(pinyinChinese.isChinese(chineseOne) && pinyinChinese.isChinese(chineseTwo)) {
            //fast-return
            if(chineseOne == chineseTwo) {
                return true;
            }

            String simpleOne = pinyinChinese.toSimple(chineseOne);
            String simpleTwo = pinyinChinese.toSimple(chineseTwo);

            List<String> tonesOne = pinyinTone.toneList(simpleOne, context);
            List<String> tonesTwo = pinyinTone.toneList(simpleTwo, context);

            // 交集大于0
            return CollectionUtil.containAny(tonesOne, tonesTwo);
        }

        return false;
    }

    @Override
    public List<Integer> toneNumList(String chinese, IPinyinContext context) {
        // 获取拼音结果
        List<String> pinyinList = this.toPinyinList(chinese, context);

        return this.buildToneNumList(pinyinList, context.tone());
    }

    @Override
    public List<Integer> toneNumList(char chinese, IPinyinContext context) {
        List<String> pinyinList = toPinyinList(chinese, context);

        return this.buildToneNumList(pinyinList, context.tone());
    }

    @Override
    public List<String> shengMuList(String chinese, IPinyinContext context) {
        final IPinyinData pinyinData = context.data();
        return normalPinyinHandler(chinese, context, new IHandler<String, String>() {
            @Override
            public String handle(String s) {
                return pinyinData.shengMu(s);
            }
        });
    }

    @Override
    public List<String> yunMuList(String chinese, IPinyinContext context) {
        final IPinyinData pinyinData = context.data();
        return normalPinyinHandler(chinese, context, new IHandler<String, String>() {
            @Override
            public String handle(String s) {
                return pinyinData.yunMu(s);
            }
        });
    }

    /**
     * 转换为拼音列表
     * @param string 字符串
     * @return 结果
     * @since 0.1.1
     */
    private List<String> toPinyinList(final String string, final IPinyinContext context) {
        if(StringUtil.isEmptyTrim(string)) {
            return Collections.emptyList();
        }

        // 基本属性
        final IPinyinSegment pinyinSegment = context.segment();
        final IPinyinChinese pinyinChinese = context.chinese();
        final IPinyinTone pinyinTone = context.tone();

        List<String> entryList = pinyinSegment.segment(string);
        List<String> resultList = Guavas.newArrayList(entryList.size());

        // 映射处理与连接
        for(String entry : entryList) {
            // 直接加入原始信息
            if(StringUtil.isEmptyTrim(entry)) {
                // 跳过空白信息
                continue;
            }

            // 这里理论上是不用判断是否为中文
            if(pinyinChinese.isChinese(entry)) {
                // 是否简体转换也应该加一个开关
                String simple = pinyinChinese.toSimple(entry);
                String tone = pinyinTone.tone(simple, context);

                resultList.add(tone);
            } else {
                resultList.add(entry);
            }
        }

        return resultList;
    }

    /**
     * 构建拼音编号列表
     * @param pinyinList 拼音结果
     * @param pinyinTone 拼音标注实现
     * @return  结果
     * @since 0.1.1
     */
    private List<Integer> buildToneNumList(List<String> pinyinList, final IPinyinTone pinyinTone) {
        List<Integer> resultList = Guavas.newArrayList(pinyinList.size());

        for(String pinyin : pinyinList) {
            Integer toneNum = pinyinTone.toneNum(pinyin);
            resultList.add(toneNum);
        }
        return resultList;
    }

    /**
     * 默认拼音处理类
     * @param chinese 中文拼音
     * @param context 上下文
     * @param handler 拼音处理类
     * @return 结果
     * @since 0.1.1
     */
    private List<String> normalPinyinHandler(final String chinese, final IPinyinContext context,
                                             final IHandler<String, String> handler) {
        // 获取拼音结果
        List<String> pinyinList = this.toPinyinList(chinese, context);
        List<String> resultList = Guavas.newArrayList(pinyinList.size());

        for (String pinyin : pinyinList) {
            String result = handler.handle(pinyin);
            resultList.add(result);
        }

        return resultList;
    }

}
