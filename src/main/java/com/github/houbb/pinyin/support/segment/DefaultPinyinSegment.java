package com.github.houbb.pinyin.support.segment;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.nlp.common.segment.ICommonSegment;
import com.github.houbb.nlp.common.segment.impl.CommonSegments;
import com.github.houbb.pinyin.spi.IPinyinSegment;
import com.github.houbb.pinyin.support.segment.trie.DefaultPinyinTrieTreeMap;

import java.util.List;

/**
 *
 * 默认的中文繁简体转换
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class DefaultPinyinSegment implements IPinyinSegment {

    /**
     * 默认分词实现
     * @since 0.0.5
     */
    private static final ICommonSegment COMMON_SEGMENT = CommonSegments.fastForward(new DefaultPinyinTrieTreeMap());

    @Override
    public List<String> segment(String string) {
        return COMMON_SEGMENT.segment(string);
    }

}
