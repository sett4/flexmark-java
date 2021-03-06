package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.toc.SimTocBlock;
import com.vladsch.flexmark.ext.toc.SimTocContent;
import com.vladsch.flexmark.formatter.CustomNodeFormatter;
import com.vladsch.flexmark.formatter.internal.*;
import com.vladsch.flexmark.util.format.Table;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.options.DataHolder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimTocNodeFormatter implements NodeFormatter {
    private final TableFormatOptions options;

    private Table myTable;

    public SimTocNodeFormatter(DataHolder options) {
        this.options = new TableFormatOptions(options);
    }

    @Override
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet<NodeFormattingHandler<? extends Node>>(Arrays.asList(
                new NodeFormattingHandler<SimTocBlock>(SimTocBlock.class, new CustomNodeFormatter<SimTocBlock>() {
                    @Override
                    public void render(SimTocBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
                        SimTocNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<SimTocContent>(SimTocContent.class, new CustomNodeFormatter<SimTocContent>() {
                    @Override
                    public void render(SimTocContent node, NodeFormatterContext context, MarkdownWriter markdown) {
                        SimTocNodeFormatter.this.render(node, context, markdown);
                    }
                })
        ));
    }

    private void render(final SimTocBlock node, final NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.openPreFormatted(false).append(node.getChars()).closePreFormatted();
    }

    private void render(final SimTocContent node, final NodeFormatterContext context, MarkdownWriter markdown) {
        return;
    }

    public static class Factory implements NodeFormatterFactory {
        @Override
        public NodeFormatter create(final DataHolder options) {
            return new SimTocNodeFormatter(options);
        }
    }
}
