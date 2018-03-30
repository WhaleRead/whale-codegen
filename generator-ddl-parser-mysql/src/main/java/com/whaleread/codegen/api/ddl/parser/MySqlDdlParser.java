package com.whaleread.codegen.api.ddl.parser;

import com.whaleread.codegen.antlr.CaseChangingCharStream;
import com.whaleread.codegen.api.ddl.DdlColumnDefinition;
import com.whaleread.codegen.api.ddl.DdlParser;
import com.whaleread.codegen.api.ddl.DdlTableDefinition;
import com.whaleread.codegen.internal.types.JdbcTypeNameTranslator;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dolphin on 2018/1/14
 */
public class MySqlDdlParser extends MySqlParserBaseListener implements DdlParser {
    private String defaultCatalog;
    private Map<String, Map<String, Map<String, DdlTableDefinition>>> metadata;

    private String currentCatalog;
    private String currentTableName;
    private DdlTableDefinition currentTable;

    @Override
    public Map<String, Map<String, Map<String, DdlTableDefinition>>> parse(String defaultCatalog, String defaultSchema, Reader in) {
        this.defaultCatalog = defaultCatalog;
        this.metadata = new HashMap<>();
        CharStream inputCharStream;
        try {
            inputCharStream = new CaseChangingCharStream(CharStreams.fromReader(in), true);
            TokenSource tokenSource = new MySqlLexer(inputCharStream);
            TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
            MySqlParser parser = new MySqlParser(inputTokenStream);
            parser.removeErrorListeners();
            parser.setErrorHandler(new BailErrorStrategy());
            parser.addErrorListener(new ConsoleErrorListener());
            ParseTree tree = parser.root();
            ParseTreeWalker.DEFAULT.walk(this, tree);
            return metadata;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enterColumnCreateTable(MySqlParser.ColumnCreateTableContext ctx) {
        MySqlParser.FullIdContext fullIdContext = ctx.tableName().fullId();
        MySqlParser.UidContext uidContext = fullIdContext.uid(0);
        String catalog = defaultCatalog;
        String tableName = uidContext.getText();
        if (fullIdContext.DOT_ID() != null) {
            catalog = tableName;
            tableName = fullIdContext.DOT_ID().getText();
        } else if (fullIdContext.uid().size() > 1) {
            catalog = tableName;
            tableName = fullIdContext.uid(1).getText();
        }
        boolean catalogQuoted = false;
        currentCatalog = catalog;
        if (catalog.charAt(0) == '`') {
            catalogQuoted = true;
            currentCatalog = catalog.substring(1, catalog.length() - 1);
        }
        boolean tableNameQuoted = false;
        currentTableName = tableName;
        if (tableName.charAt(0) == '`') {
            tableNameQuoted = true;
            currentTableName = tableName.substring(1, tableName.length() - 1);
        }
        currentTable = new DdlTableDefinition(currentCatalog, null, currentTableName);
        currentTable.setCatalogQuoted(catalogQuoted);
        currentTable.setNameQuoted(tableNameQuoted);

        Map<String, DdlTableDefinition> tables = metadata.computeIfAbsent(currentCatalog, key -> new HashMap<>()).computeIfAbsent("", (key) -> new HashMap<>());
        tables.put(currentTableName, currentTable);
    }

    @Override
    public void enterColumnDeclaration(MySqlParser.ColumnDeclarationContext ctx) {
        String columnName = ctx.uid().getText();
        boolean quoted = false;
        if (columnName.charAt(0) == '`') {
            columnName = columnName.substring(1, columnName.length() - 1);
            quoted = true;
        }
        DdlColumnDefinition column = new DdlColumnDefinition(currentCatalog, null, currentTableName, columnName);
        column.setQuoted(quoted);
        currentTable.addColumn(column);
        column.setOriginalPosition(currentTable.getColumns().size() - 1);
        MySqlParser.ColumnDefinitionContext columnDefinitionContext = ctx.columnDefinition();
        DataTypeVisitor dataTypeVisitor = new DataTypeVisitor(column);
        dataTypeVisitor.visit(columnDefinitionContext.dataType());
        ColumnConstraintVisitor columnConstraintVisitor = new ColumnConstraintVisitor(column);
        columnDefinitionContext.columnConstraint().forEach(columnConstraintVisitor::visit);
        String originType = column.getOriginType().toUpperCase();
        if("INT".equals(originType)) {
            originType = "INTEGER";
        }
        column.setOriginType(originType);
        column.setType(JdbcTypeNameTranslator.getJdbcType(column.getOriginType()));
        if (column.isPrimaryKey()) {
            currentTable.addPrimaryKey(column.getName());
        }
    }

    @Override
    public void enterConstraintDeclaration(MySqlParser.ConstraintDeclarationContext ctx) {
        new TableConstraintVisitor(currentTable).visit(ctx);
    }

    @Override
    public void enterTableOptionComment(MySqlParser.TableOptionCommentContext ctx) {
        String remarks = ctx.STRING_LITERAL().getText();
        currentTable.setRemarks(remarks.substring(1, remarks.length() - 1));
    }

    //
//    @Override
//    public void enterCreate_definition(MySqlParser.Create_definitionContext ctx) {
//        MySqlParser.Col_nameContext col_nameContext = ctx.col_name();
//        if (col_nameContext != null) {
//            MySqlParser.Column_definitionContext column_definitionContext = ctx.column_definition();
//            ColumnMetaData column = new ColumnMetaData();
//            column.setColumnName(StringUtils.unwrapBackQuote(col_nameContext.identity().getText()));
//            MySqlParser.Data_typeContext data_typeContext = ctx.column_definition().data_type();
//            MySqlParser.Single_typeContext single_typeContext = data_typeContext.single_type();
//            if (single_typeContext != null) {
//                column.setJdbcType(MySQLTypes.getJdbcType(single_typeContext.SQL_TYPE().getText()));
//                if (single_typeContext.length() != null) {
//                    column.setLength(Integer.parseInt(single_typeContext.length().getText()));
//                }
//                if (single_typeContext.decimals() != null) {
//                    column.setDecimals(Integer.parseInt(single_typeContext.decimals().getText()));
//                }
//                if (single_typeContext.charset_name() != null) {
//                    column.setCharset(single_typeContext.charset_name().getText());
//                }
//                if (single_typeContext.collation_name() != null) {
//                    column.setCollation(single_typeContext.collation_name().getText());
//                }
//            } else {
//                MySqlParser.Enum_typeContext enum_typeContext = data_typeContext.enum_type();
//                column.setJdbcType(MySQLTypes.getJdbcType(enum_typeContext.SQL_TYPE().getText()));
//                if (enum_typeContext.charset_name() != null) {
//                    column.setCharset(enum_typeContext.charset_name().getText());
//                }
//                if (enum_typeContext.collation_name() != null) {
//                    column.setCollation(enum_typeContext.collation_name().getText());
//                }
//                List<String> enumValues = new ArrayList<>(enum_typeContext.enum_value().size());
//                column.setEnumValues(enumValues);
//                for (MySqlParser.Enum_valueContext enum_valueContext : enum_typeContext.enum_value()) {
//                    enumValues.add(enum_valueContext.getText());
//                }
//            }
//            if (column_definitionContext.NOT() != null) {
//                column.setNullable(0);
//            }
//            if (column_definitionContext.default_value() != null) {
//                column.setDefaultValue(column_definitionContext.default_value().getText());
//            }
//            if (column_definitionContext.comment_string() != null) {
//                column.setRemarks(StringUtils.unwrapQuote(column_definitionContext.comment_string().getText()));
//            }
//            if(column_definitionContext.AUTO_INCREMENT() != null) {
//                column.setAutoIncrement("YES");
//            }
//            column.setCatalog(current.getCatalog());
//            column.setSchema(current.getSchema());
//            column.setTableName(current.getTableName());
//            current.getColumns().add(column);
//            return;
//        }
//        if (ctx.PRIMARY() != null) {
//            current.setPrimaryKeys(ctx.index_col_name().stream().map(RuleContext::getText).map(StringUtils::unwrapBackQuote).collect(Collectors.toList()));
//        }
//        super.enterCreate_definition(ctx);
//    }
//
//    @Override
//    public void enterTable_option(MySqlParser.Table_optionContext ctx) {
//        if (ctx.comment_string() != null) {
//            current.setRemarks(StringUtils.unwrapQuote(ctx.comment_string().getText()));
//        }
//    }
}
