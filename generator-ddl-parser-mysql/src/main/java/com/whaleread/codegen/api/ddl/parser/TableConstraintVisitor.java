package com.whaleread.codegen.api.ddl.parser;

import com.whaleread.codegen.api.ddl.DdlTableDefinition;

import java.util.stream.Collectors;

/**
 * Created by Dolphin on 2018/1/17
 */
public class TableConstraintVisitor extends MySqlParserBaseVisitor<Void> {
    private final DdlTableDefinition table;

    public TableConstraintVisitor(DdlTableDefinition table) {
        this.table = table;
    }

    @Override
    public Void visitPrimaryKeyTableConstraint(MySqlParser.PrimaryKeyTableConstraintContext ctx) {
        table.setPrimaryKeys(ctx.indexColumnNames().indexColumnName().stream().map(c -> c.uid().getText()).map(k -> k.charAt(0) == '`' ? k.substring(1, k.length() - 1) : k).collect(Collectors.toList()));
//        if (ctx.CONSTRAINT() != null) {
//            String columnName = StringUtility.unwrapBackQuote(ctx.name.getText());
//            DdlColumnDefinition column = table.findColumn(columnName);
//            if (column != null) {
//                column.setKey(ctx.CONSTRAINT().getText() + " " + ctx.uid().getText());
//            }
//        }
        return null;
    }

    @Override
    public Void visitUniqueKeyTableConstraint(MySqlParser.UniqueKeyTableConstraintContext ctx) {
        return super.visitUniqueKeyTableConstraint(ctx);
    }

    @Override
    public Void visitForeignKeyTableConstraint(MySqlParser.ForeignKeyTableConstraintContext ctx) {
        return super.visitForeignKeyTableConstraint(ctx);
    }

    @Override
    public Void visitCheckTableConstraint(MySqlParser.CheckTableConstraintContext ctx) {
        return super.visitCheckTableConstraint(ctx);
    }
}
