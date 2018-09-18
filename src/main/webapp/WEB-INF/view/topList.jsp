<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>${rateAllSumThree}|${rateAllSum}</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <style>
        td {
            padding: 6px 5px;
            background-color: #fff;
            border: 1px solid #ddd;
            min-width: 10px;
            text-align: center;
        }

        th {
            border: 1px solid #ddd;
            height: 45px;
            background: azure;
            padding: 8px 6px;
            text-align: center;
        }

        input {
            height: 30px;
            padding: 3px 5px;
            margin: 3px 5px;
            min-width: 100px;
        }

        select {
            height: 30px;
            padding: 3px 5px;
            margin: 3px 5px;
            min-width: 100px;
        }
    </style>
</head>
<body>


<form action="/day/topList" method="get">
    <div style="margin:0 auto; width: 90%;text-align: center;">

        <table style="width: 100%;">
            <tr>
                <th>开始日期：
                    <select name="dateStart">
                        <option value="">请选择</option>
                        <c:forEach items="${dateStarts}" var="item" varStatus="var">
                            <option value="${item}"
                                    <c:if test="${item==dateStart}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>
                <th>结束日期：
                    <select name="dateEnd">
                        <option value="">请选择</option>
                        <c:forEach items="${dateEnds}" var="item" varStatus="var">
                            <option value="${item}" <c:if test="${item==dateEnd}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>

                <th>新股天：
                    <select name="day">
                        <option value="">请选择</option>
                            <option value="35" <c:if test="${day==35}">selected</c:if>>35天</option>
                            <option value="34" <c:if test="${day==34}">selected</c:if> >34天</option>
                            <option value="33" <c:if test="${day==33}">selected</c:if> >33天</option>
                            <option value="32" <c:if test="${day==32}">selected</c:if> >32天</option>
                            <option value="31" <c:if test="${day==31}">selected</c:if> >31天</option>
                            <option value="30" <c:if test="${day==30}">selected</c:if> >30天</option>
                            <option value="29" <c:if test="${day==29}">selected</c:if> >29天</option>
                            <option value="28" <c:if test="${day==28}">selected</c:if> >28天</option>
                            <option value="27" <c:if test="${day==27}">selected</c:if> >27天</option>
                            <option value="26" <c:if test="${day==26}">selected</c:if> >26天</option>
                            <option value="25" <c:if test="${day==25}">selected</c:if> >25天</option>
                            <option value="24" <c:if test="${day==24}">selected</c:if> >24天</option>
                            <option value="23" <c:if test="${day==23}">selected</c:if> >23天</option>
                            <option value="22" <c:if test="${day==22}">selected</c:if> >22天</option>
                            <option value="21" <c:if test="${day==21}">selected</c:if> >21天</option>
                            <option value="20" <c:if test="${day==20}">selected</c:if> >20天</option>
                            <option value="19" <c:if test="${day==19}">selected</c:if> >19天</option>
                            <option value="18" <c:if test="${day==18}">selected</c:if> >18天</option>
                            <option value="17" <c:if test="${day==17}">selected</c:if> >17天</option>
                            <option value="16" <c:if test="${day==16}">selected</c:if> >16天</option>
                            <option value="15" <c:if test="${day==15}">selected</c:if> >15天</option>
                            <option value="14" <c:if test="${day==14}">selected</c:if> >14天</option>
                            <option value="13" <c:if test="${day==13}">selected</c:if> >13天</option>
                            <option value="12" <c:if test="${day==12}">selected</c:if> >12天</option>
                            <option value="11" <c:if test="${day==11}">selected</c:if> >11天</option>
                            <option value="10" <c:if test="${day==10}">selected</c:if> >10天</option>
                            <option value="9" <c:if test="${day==9}">selected</c:if>>9天</option>
                            <option value="8" <c:if test="${day==8}">selected</c:if>>8天</option>
                            <option value="7" <c:if test="${day==7}">selected</c:if>>7天</option>
                            <option value="6" <c:if test="${day==6}">selected</c:if>>6天</option>
                    </select>
                </th>

                <th><input type="submit" value="查询"></th>
            </tr>
        </table>

        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>index</th>
                <th>code</th>
                <th>preEnd</th>
                <th>nextStart</th>
                <th>nextMax</th>
                <th>nextEnd</th>
                <th>preRate</th>
                <th>nextRate</th>
                <th>threeRate</th>
                <th>threeMaxPrice</th>
                <th>threeEndPrice</th>
                <th>preInflow</th>
                <th>nextInflow</th>
                <th>date</th>
            </tr>

        <%--item 前，itemOld后--%>
            <c:forEach items="${dayValues}" var="item"  step="1"  varStatus="var">
            <tr>
                    <%--当前--%>
                <td style="<c:choose>
                        <c:when test="${item.nextRate>0}">
                            background: red;
                        </c:when>
                        <c:when test="${item.nextMaxPrice>item.preEndPrice}">
                            background: yellow;
                        </c:when>
                <c:when test="${item.nextMaxPrice<item.preEndPrice}">
                        background: green;
                        </c:when>
                        </c:choose>">${var.count}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='600' or fn:substring(item.companyCode ,0,2)=='60'}">
                                    <a href="http://quote.eastmoney.com/concept/sh${item.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                       target="_blank">${item.companyCode}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="http://quote.eastmoney.com/concept/sz${item.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                       target="_blank">${item.companyCode}</a>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${item.isNew==1}"><span style="color: red">n</span></c:if>
                        </td>
                <td>${item.preEndPrice}</td>
                <td>${item.nextStartPrice}</td>
                <td>${item.nextMaxPrice}</td>
                <td>${item.nextEndPrice}</td>
                <td>${item.preRate}</td>
                <td  style="<c:choose>
                <c:when test="${item.nextRate>0}">
                        color: red;
                </c:when>
                <c:when test="${item.nextRate<0}">
                        color: green;
                </c:when>
                        </c:choose>">${item.nextRate}</td>
                <td  style="<c:choose>
                <c:when test="${item.threeRate>0}">
                        color: red;
                </c:when>
                <c:when test="${item.threeRate<0}">
                        color: green;
                </c:when>
                        </c:choose>">${item.threeRate}</td>
                <td>${item.threeMaxPrice}</td>
                <td>${item.threeEndPrice}</td>
                <td >${item.preTotalMoney}</td>
                <td >${item.nextTotalMoney}</td>
                <td>${item.date}</td>
                </tr>
            </c:forEach>


            </tbody>
        </table><!-- /.table -->
    </div>








</form>


<script type="text/javascript"
        src="${ctx}assets/js/common/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${ctx}assets/js/common/bootstrap.min.js"></script>

</body>
</html>
