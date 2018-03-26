package com.whaleread.codegen.api.ddl.parser;

import com.whaleread.codegen.api.ddl.DdlColumnDefinition;

/**
 * Created by Dolphin on 2018/1/17
 */
public class ColumnConstraintVisitor extends MySqlParserBaseVisitor<Void> {
    private final DdlColumnDefinition column;

    public ColumnConstraintVisitor(DdlColumnDefinition column) {
        this.column = column;
    }

    @Override
    public Void visitNullColumnConstraint(MySqlParser.NullColumnConstraintContext ctx) {
        column.setNullable(ctx.nullNotnull().NOT() == null ? 1 : 0);
        return null;
    }

    @Override
    public Void visitDefaultColumnConstraint(MySqlParser.DefaultColumnConstraintContext ctx) {
        column.setDefaultValue(ctx.defaultValue().getText());
        return null;
    }

    @Override
    public Void visitAutoIncrementColumnConstraint(MySqlParser.AutoIncrementColumnConstraintContext ctx) {
        column.setAutoIncrement(true);
        return null;
    }

    @Override
    public Void visitPrimaryKeyColumnConstraint(MySqlParser.PrimaryKeyColumnConstraintContext ctx) {
        column.setPrimaryKey(ctx.PRIMARY() != null);
        column.setKey(ctx.getText());
        return null;
    }

    @Override
    public Void visitUniqueKeyColumnConstraint(MySqlParser.UniqueKeyColumnConstraintContext ctx) {
        column.setKey(ctx.getText());
        return null;
    }

    @Override
    public Void visitCommentColumnConstraint(MySqlParser.CommentColumnConstraintContext ctx) {
        String comment = ctx.STRING_LITERAL().getText();
        column.setRemarks(comment.substring(1, comment.length() - 1));
        return null;
    }

    @Override
    public Void visitFormatColumnConstraint(MySqlParser.FormatColumnConstraintContext ctx) {
        return super.visitFormatColumnConstraint(ctx);
    }

    @Override
    public Void visitStorageColumnConstraint(MySqlParser.StorageColumnConstraintContext ctx) {
        return super.visitStorageColumnConstraint(ctx);
    }

    @Override
    public Void visitReferenceColumnConstraint(MySqlParser.ReferenceColumnConstraintContext ctx) {
        return super.visitReferenceColumnConstraint(ctx);
    }
}
