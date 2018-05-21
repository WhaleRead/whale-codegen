package com.whaleread.codegen.api.ddl.parser;

import com.whaleread.codegen.api.ddl.DdlColumnDefinition;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Collectors;

/**
 * Created by Dolphin on 2018/1/17
 */
public class DataTypeVisitor extends MySqlParserBaseVisitor<Void> {
    private final DdlColumnDefinition column;

    public DataTypeVisitor(DdlColumnDefinition column) {
        this.column = column;
    }

    @Override
    public Void visitStringDataType(MySqlParser.StringDataTypeContext ctx) {
        column.setOriginType(ctx.typeName.getText());
        if(ctx.lengthOneDimension() != null) {
            int size = Integer.parseInt(ctx.lengthOneDimension().decimalLiteral().getText());
            column.setSize(size);
        }
        return null;
    }

    @Override
    public Void visitDimensionDataType(MySqlParser.DimensionDataTypeContext ctx) {
        column.setOriginType(ctx.typeName.getText());
        int size = 0;
        int scale = 0;
        if (ctx.lengthOneDimension() != null) {
            size = Integer.parseInt(ctx.lengthOneDimension().decimalLiteral().getText());
        } else if (ctx.lengthTwoDimension() != null) {
            size = Integer.parseInt(ctx.lengthTwoDimension().decimalLiteral(0).getText());
            scale = Integer.parseInt(ctx.lengthTwoDimension().decimalLiteral(1).getText());
        } else if (ctx.lengthTwoOptionalDimension() != null) {
            size = Integer.parseInt(ctx.lengthTwoDimension().decimalLiteral(0).getText());
            if (ctx.lengthTwoDimension().decimalLiteral().size() == 2) {
                scale = Integer.parseInt(ctx.lengthTwoDimension().decimalLiteral(1).getText());
            }
        }
        column.setSize(size);
        column.setScale(scale);
        return null;
    }

    @Override
    public Void visitSimpleDataType(MySqlParser.SimpleDataTypeContext ctx) {
        column.setOriginType(ctx.typeName.getText());
        return null;
    }

    @Override
    public Void visitCollectionDataType(MySqlParser.CollectionDataTypeContext ctx) {
        column.setOriginType(ctx.typeName.getText());
        column.setEnums(ctx.STRING_LITERAL().stream().map(ParseTree::getText).collect(Collectors.toList()));
        return null;
    }

    @Override
    public Void visitSpatialDataType(MySqlParser.SpatialDataTypeContext ctx) {
        column.setOriginType(ctx.typeName.getText());
        return null;
    }
}
