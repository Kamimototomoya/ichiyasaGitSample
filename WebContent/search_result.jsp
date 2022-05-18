<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Result,java.util.List"%>
<%
//リクエストスコープに保存された情報を取得
List<Result> table = (List<Result>) request.getAttribute("table");
String errorMsg = (String) request.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/search_result.css">
<title>契約管理システム | 契約履歴検索</title>
</head>
<body>

	<%if(errorMsg!=null) { %>
		<p><%=errorMsg%></p>
	<% }else {%>
		<table id="result_table">
		<tr>
			<th>契約番号</th><th>契約日</th><th>契約者名</th><th>保険料合計</th><th>詳細</th>
		</tr>



		<% for(int i=0; i<table.size(); i++){ %>
			<% int contractId=table.get(i).getContractId();%>

		<tr>
			<td align="right"><%=contractId%></td>
			<td align="center"><%=table.get(i).getContractDate()%></td>
			<td align="left"><%=table.get(i).getCustomerName()%></td>
			<td align="right">
				<script type="text/javascript">
					  var text ="<%=table.get(i).getTotal()%>".replace(/(\d)(?=(\d\d\d)+$)/g, "$1,");
					  document.write(text);
				</script>
			</td>
			<td><a href="/ShowDetailServlet?contract_id=<%=contractId%>" target="_blank">見る</a></td>
		</tr>
		<% } %>


	</table>

	<%}%>




</body>
</html>