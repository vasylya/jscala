package org.jscala

import scala.util.matching.Regex

sealed trait JsAst

sealed trait JsStmt extends JsAst
sealed trait JsExpr extends JsAst
sealed trait JsLit extends JsExpr

case class JsBool(value: Boolean) extends JsLit
case class JsString(value: String) extends JsLit
case class JsNum(value: Double, isFloat: Boolean) extends JsLit
case class JsArray(values: List[JsExpr]) extends JsLit
case object JsUnit extends JsLit with JsStmt

case class JsIdent(ident: String) extends JsExpr
case class JsAccess(qualifier: JsExpr, key: JsExpr) extends JsExpr
case class JsSelect(qualifier: JsExpr, name: String) extends JsExpr
case class JsUnOp(operator: String, operand: JsExpr) extends JsExpr
case class JsBinOp(operator: String, lhs: JsExpr, rhs: JsExpr) extends JsExpr
case class JsCall(callee: JsExpr, params: List[JsExpr]) extends JsExpr
case class JsNew(ctor: JsCall) extends JsExpr
case class JsAnonFunDecl(params: List[String], body: JsStmt) extends JsExpr
case class JsAnonObjDecl(fields: Map[String, JsExpr]) extends JsExpr

case class JsBlock(stmts: List[JsStmt]) extends JsStmt
case class JsExprStmt(jsExpr: JsExpr) extends JsStmt
case class JsIf(cond: JsExpr, `then`: JsStmt, `else`: Option[JsStmt]) extends JsStmt
case class JsWhile(cond: JsExpr, body: JsStmt) extends JsStmt
case class JsFor(coll: JsExpr, ident: JsIdent, body: JsStmt) extends JsStmt
case class JsVarDef(ident: String, initializer: JsExpr) extends JsStmt
case class JsFunDecl(ident: String, params: List[String], body: JsStmt) extends JsStmt
case class JsReturn(jsExpr: JsExpr) extends JsStmt
