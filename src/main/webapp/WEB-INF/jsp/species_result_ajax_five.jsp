<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>종목록생성기</title>
    <script type="text/javascript">

      $(function () {
        $('#Modal').modal('show')
      });


    </script>
</head>
<body>

<div class="table-container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th class="text-center" width="200px">학명<br>(Scientific name)</th>
            <th class="text-center" width="150px">국명<br>(common name)</th>
            <c:forEach var="item" items="${group_name}" varStatus="i">
                <th class="text-center" width="100px">${item}</th>
            </c:forEach>
            <th class="text-center" width="100px">전체</th>
            <c:if test="${filter != '저서동물'}"><th class="text-center" width="50px">생활형</th></c:if>
            <th class="text-center" width="80px">비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="moon" items="${result.keySet()}" varStatus="f">
            <tr class="moon">
                <td>&nbsp;Phylum ${moon.enName}</td>
                <td>&nbsp;${moon.korName}</td>
                <c:forEach var="item" items="${group_name}" varStatus="i">
                    <td></td>
                </c:forEach>
                <td></td>
                <td></td>
                <c:if test="${filter != '저서동물'}"><td></td></c:if>
            </tr>
            <c:forEach var="gang" items="${result.get(moon).keySet()}" varStatus="f">
                <tr class="gang">
                    <td>&nbsp;Class ${gang.enName}</td>
                    <td>&nbsp;${gang.korName}</td>
                    <c:forEach var="item" items="${group_name}" varStatus="i">
                        <td></td>
                    </c:forEach>
                    <td></td>
                    <td></td>
                    <c:if test="${filter != '저서동물'}"><td></td></c:if>
                </tr>
                <c:forEach var="family" items="${result.get(moon).get(gang).keySet()}" varStatus="f">
                    <tr class="family">
                        <td>&nbsp;&nbsp;Order ${family.enName}</td>
                        <td>&nbsp;&nbsp;${family.korName}</td>
                        <c:forEach var="item" items="${group_name}" varStatus="i">
                            <td></td>
                        </c:forEach>
                        <td></td>
                        <td></td>
                        <c:if test="${filter != '저서동물'}"><td></td></c:if>
                    </tr>
                    <c:forEach var="order" items="${result.get(moon).get(gang).get(family).keySet()}" varStatus="o">
                        <tr class="order">
                            <td>&nbsp;&nbsp;&nbsp;Family ${order.enName}</td>
                            <td>&nbsp;&nbsp;&nbsp;${order.korName}</td>
                            <c:forEach var="item" items="${group_name}" varStatus="i">
                                <td></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <c:if test="${filter != '저서동물'}"><td></td></c:if>
                        </tr>
                        <c:forEach var="item" items="${result.get(moon).get(gang).get(family).get(order)}"
                                   varStatus="i">
                            <tr class="f-o-item">
                                <td class="scName">&nbsp;&nbsp;&nbsp;
                                    <c:forEach var="sc" items="${item.getScientificNameArray()}" varStatus="idx">
                <span
                        <c:if test="${idx.index == 0 || idx.index == 1 ||
                 (idx.index > 2 && (item.getScientificNameArray()[idx.index - 1] == 'var.') ||
                 item.getScientificNameArray()[idx.index - 1] == 'for.' ||
                 item.getScientificNameArray()[idx.index - 1] == 'subsp.')}">class="font-italic"</c:if>  >
                        ${sc}
                </span>
                                    </c:forEach>
                                </td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;${item.realName}</td>
                                <c:forEach var="name" items="${group_name}" varStatus="i">
                                    <td class="text-center"><c:if test="${item.getGroup().contains(name)}">O</c:if></td>
                                </c:forEach>
                                <td class="text-center">O</td>
                                <c:if test="${filter != '저서동물'}"><td class="text-center">${item.lifeType}</td></c:if>
                                <td class="text-center">${item.getETC()}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
        </c:forEach>
        <tr>
            <td class="text-center" colspan="2">총 합</td>
            <c:forEach var="name" items="${group_name}" varStatus="i">
                <td class="text-center">${totalMap.get(name).getTotal()}</td>
            </c:forEach>
            <td class="text-center">${totalMap.get('totalKSH').getTotal()}</td>
            <td class="text-center" colspan="10"></td>
        </tr>
        </tbody>
    </table>
</div>

<div class="modal fade bs-example-modal-sm" id="Modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>검색안된 종이 ${noneCount}개 있습니다.</h6>
                <textarea class='form-control' rows='10'>${none}</textarea>
            </div>
        </div>
    </div>
</div>

</body>
</html>