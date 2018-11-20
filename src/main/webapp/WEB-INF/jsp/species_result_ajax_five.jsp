<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>종목록생성기</title>
    <script type="text/javascript">

      $(function () {
        // $('#Modal').modal('show')
        $("#graph").click(function (e) {
          $('#graphModal').modal('show')
        });
        $("#filter2").change(function() {
          var filter = $( "#filter2" ).val();
          var data = [];

          if(filter === '문') {
            <c:forEach var="m" items="${moonCount.keySet()}" varStatus="f">
            <c:if test="${m != 'total'}">
            var num = ${ moonCount.get(m) / moonCount.get("total") * 100}
            var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
            data.push(d)
            </c:if>
            </c:forEach>
          } else if(filter === '강') {
            <c:forEach var="m" items="${gangCount.keySet()}" varStatus="f">
            <c:if test="${m != 'total'}">
            var num = ${ gangCount.get(m) / gangCount.get("total") * 100}
            var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
            data.push(d)
            </c:if>
            </c:forEach>
          } else if(filter === '목') {
            <c:forEach var="m" items="${mokCount.keySet()}" varStatus="f">
            <c:if test="${m != 'total'}">
            var num = ${ mokCount.get(m) / mokCount.get("total") * 100}
            var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
            data.push(d)
            </c:if>
            </c:forEach>
          } else if(filter === '과') {
            <c:forEach var="m" items="${guaCount.keySet()}" varStatus="f">
            <c:if test="${m != 'total'}">
            var num = ${ guaCount.get(m) / guaCount.get("total") * 100}
            var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
            data.push(d)
            </c:if>
            </c:forEach>
          } else {
            <c:forEach var="m" items="${myeljongCount.keySet()}" varStatus="f">
            <c:if test="${m != 'total'}">
            var num = ${ myeljongCount.get(m) / myeljongCount.get("total") * 100}
            var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
            data.push(d)
            </c:if>
            </c:forEach>
          }

          var chart2 = new CanvasJS.Chart("chartContainer", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
              text: filter
            },
            data: [{
              type: "pie",
              startAngle: 0,
              toolTipContent: "<b>{label}</b>: {y}%",
              showInLegend: "true",
              legendText: "{label}",
              indexLabelFontSize: 14,
              indexLabel: "{label} - {y}%",
              dataPoints: data
            }]
          });
          chart2.render();
        });
      });
      var data = [];
      <c:forEach var="m" items="${myeljongCount.keySet()}" varStatus="f">
        <c:if test="${m != 'total'}">
            var num = ${ myeljongCount.get(m) / myeljongCount.get("total") * 100}
            var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
            data.push(d)
        </c:if>
      </c:forEach>
      var chart = new CanvasJS.Chart("chartContainer", {
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        exportEnabled: true,
        animationEnabled: true,
        title: {
          text: "멸종위기"
        },
        data: [{
          type: "pie",
          startAngle: 0,
          toolTipContent: "<b>{label}</b>: {y}%",
          showInLegend: "true",
          legendText: "{label}",
          indexLabelFontSize: 14,
          indexLabel: "{label} - {y}%",
          dataPoints: data
        }]
      });
      $('#graphModal').on('shown.bs.modal', function () {
        chart.render();
      });

      function exportTableToExcel(tableID, filename){
        var downloadLink;
        var dataType = 'application/vnd.ms-excel';
        var tableSelect = document.getElementById(tableID);
        var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
        var date = new Date();
        var dateTime = '_'+date.getDate()+'_'+date.getHours()+'_'+date.getMinutes()+'_'+date.getSeconds()
        // Specify file name
        filename = filename?filename+dateTime+'.xls':'excel_data.xls';

        // Create download link element
        downloadLink = document.createElement("a");

        document.body.appendChild(downloadLink);

        if(navigator.msSaveOrOpenBlob){
          var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
          });
          navigator.msSaveOrOpenBlob( blob, filename);
        }else{
          // Create a link to the file
          downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

          // Setting the file name
          downloadLink.download = filename;

          //triggering the function
          downloadLink.click();
        }
      }


    </script>
    <style>
        .scName a {
            color: black;
        }
        .scName a:hover {
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="table-container">
    <div style="margin-bottom: 20px">
        <button class="btn btn-default" id="graph" style="font-weight: 400 !important;">그래프만들기</button>
        <button class="btn btn-primary" id="save" style="font-weight: 400 !important; margin-left: 20px" onclick="exportTableToExcel('headerTable', 'ecogeo-jong')">저장하기</button>
    </div>
    <table class="table table-bordered" id="headerTable">
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
                                <td class="scName"><a href="/manage?filter=${filter}&name=${item.getScientificName()}" target="_blank">&nbsp;&nbsp;&nbsp;
                                    <c:forEach var="sc" items="${item.getScientificNameArray()}" varStatus="idx">
                <span
                        <c:if test="${idx.index == 0 || idx.index == 1 ||
                 (idx.index > 2 && (item.getScientificNameArray()[idx.index - 1] == 'var.') ||
                 item.getScientificNameArray()[idx.index - 1] == 'for.' ||
                 item.getScientificNameArray()[idx.index - 1] == 'subsp.')}">class="font-italic"</c:if>  >
                        ${sc}
                </span>
                                    </c:forEach>
                                </a>
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

<div class="modal fade bs-example-modal-lg" id="graphModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                그래프
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div>
                    분류 조건
                    <select id="filter2" class="form-control" style="width: 150px; display: inline-block; height: 34px;">
                        <option>멸종위기</option>
                        <option>문</option>
                        <option>강</option>
                        <option>목</option>
                        <option>과</option>
                    </select>
                </div>
                <div>
                    <div id="chartContainer" style="height: 400px; width: 100%;"></div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>