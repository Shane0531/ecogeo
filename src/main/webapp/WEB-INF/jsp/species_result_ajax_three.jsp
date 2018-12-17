<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>종목록생성기</title>
  <script type="text/javascript">

    $(function () {
      $('#Modal').modal('show')
      $("#graph").click(function (e) {
        $('#graphModal').modal('show')
      });
      $("#filter2").change(function() {
        var filter = $( "#filter2" ).val();
        var data = [];

        if(filter === '멸종위기') {
          <c:forEach var="m" items="${myeljongCount.keySet()}" varStatus="f">
          <c:if test="${m != 'total'}">
          var num = ${ myeljongCount.get(m) / myeljongCount.get("total") * 100}
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
          <c:forEach var="m" items="${chenyeonCount.keySet()}" varStatus="f">
          <c:if test="${m != 'total'}">
          var num = ${ chenyeonCount.get(m) / chenyeonCount.get("total") * 100}
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
    <c:forEach var="m" items="${chenyeonCount.keySet()}" varStatus="f">
    <c:if test="${m != 'total'}">
    var num = ${ chenyeonCount.get(m) / chenyeonCount.get("total") * 100}
    var d = {y: parseFloat(num).toFixed(1), label: '${m}'}
    data.push(d)
    </c:if>
    </c:forEach>
    var chart = new CanvasJS.Chart("chartContainer", {
      theme: "light2", // "light1", "light2", "dark1", "dark2"
      exportEnabled: true,
      animationEnabled: true,
      title: {
        text: "천연기념물"
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
  <table class="table table-bordered">
    <thead>
    <tr>
      <th class="text-center" width="200px">학명<br>(Scientific name)</th>
      <th class="text-center" width="150px">국명<br>(common name)</th>
      <c:forEach var="item" items="${group_name}" varStatus="i">
        <th class="text-center" width="100px">${item}</th>
      </c:forEach>
      <th class="text-center" width="100px">전체</th>
      <c:if test="${filter == '조류'}"><th class="text-center" width="50px">도래현황</th></c:if>
      <th class="text-center" width="80px">비고</th>
    </tr>
    </thead>
    <tbody>
      <c:forEach var="family" items="${result.keySet()}" varStatus="f">
        <tr class="family">
          <td>&nbsp;Order ${family.enName}</td>
          <td>&nbsp;${family.korName}</td>
          <c:forEach var="item" items="${group_name}" varStatus="i">
            <td></td>
          </c:forEach>
          <td></td>
          <td></td>
          <c:if test="${filter == '조류'}"><td></td></c:if>
        </tr>
        <c:forEach var="order" items="${result.get(family).keySet()}" varStatus="o">
          <tr class="order">
            <td>&nbsp;&nbsp;Family ${order.enName}</td>
            <td>&nbsp;&nbsp;${order.korName}</td>
            <c:forEach var="item" items="${group_name}" varStatus="i">
              <td></td>
            </c:forEach>
            <td></td>
            <td></td>
            <c:if test="${filter == '조류'}"><td></td></c:if>
          </tr>
          <c:forEach var="item" items="${result.get(family).get(order)}" varStatus="i">
            <tr class="f-o-item">
                <td class="scName"><a href="/manage?filter=${filter}&name=${item.getScientificName()}" target="_blank">&nbsp;&nbsp;
              <c:forEach var="sc" items="${item.getScientificNameArray()}" varStatus="idx">
                <span  <c:if test="${idx.index == 0 || idx.index == 1 ||
                 (idx.index > 2 && (item.getScientificNameArray()[idx.index - 1] == 'var.') ||
                 item.getScientificNameArray()[idx.index - 1] == 'for.' ||
                 item.getScientificNameArray()[idx.index - 1] == 'subsp.')}">class="font-italic"</c:if>  >
                  ${sc}
                </span>
              </c:forEach>
                </a>
              </td>
              <td>&nbsp;&nbsp;&nbsp;${item.realName}</td>
              <c:forEach var="name" items="${group_name}" varStatus="i">
                <td class="text-center"><c:if test="${item.getGroup().contains(name)}">O</c:if></td>
              </c:forEach>
              <td class="text-center">O</td>
              <c:if test="${filter == '조류'}"><td class="text-center">${item.currentStatus}</td></c:if>
              <td class="text-center">
                <c:if test="${filter =='담수어류'}">
                  <c:forEach var="etc" items="${item.getETCList()}" varStatus="ie">
                    ${etc} <c:if test="${ ie.index+1 != item.getETCList().size()}">,</c:if>
                  </c:forEach>
                </c:if>
                <c:if test="${filter !='담수어류'}">${item.getETC()}</c:if>
              </td>
            </tr>
          </c:forEach>
        </c:forEach>
      </c:forEach>
      <tr>
        <td class="text-center" colspan="2">총 합</td>
        <c:forEach var="name" items="${group_name}" varStatus="i">
          <td class="text-center">${totalMap.get(name).getTotal()}</td>
        </c:forEach>
        <td class="text-center">${totalMap.get('totalKSH').getTotal()}</td>
        <td class="text-center" colspan="3"></td>
      </tr>
    </tbody>
  </table>
</div>

<div class="modal fade bs-example-modal-sm" id="Modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        <h6>검색안된 종이 ${noneCount}개 있습니다.</h6>
        <textarea class='form-control' rows='3'>${none}</textarea>
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
            <option>천연기념물</option>
            <option>멸종위기</option>
            <option>목</option>
            <option>과</option>
          </select>
        </div>
        <div>
          <div id="chartContainer" style="height: 450px; width: 100%;"></div>
        </div>
      </div>
    </div>
  </div>
</div>

<table class="table table-bordered" id="headerTable" style="display: none">
  <thead>
  <tr>
    <th class="text-center" width="200px">학명<br>(Scientific name)</th>
    <th class="text-center" width="150px">국명<br>(common name)</th>
    <c:forEach var="item" items="${group_name}" varStatus="i">
      <th class="text-center" width="100px">${item}</th>
    </c:forEach>
    <th class="text-center" width="100px">전체</th>
    <c:if test="${filter == '조류'}"><th class="text-center" width="50px">도래현황</th></c:if>
    <th class="text-center" width="80px">비고</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="family" items="${result.keySet()}" varStatus="f">
    <tr class="family">
      <td>Order ${family.enName}</td>
      <td>${family.korName}</td>
      <c:forEach var="item" items="${group_name}" varStatus="i">
        <td></td>
      </c:forEach>
      <td></td>
      <td></td>
      <c:if test="${filter == '조류'}"><td></td></c:if>
    </tr>
    <c:forEach var="order" items="${result.get(family).keySet()}" varStatus="o">
      <tr class="order">
        <td>Family ${order.enName}</td>
        <td>${order.korName}</td>
        <c:forEach var="item" items="${group_name}" varStatus="i">
          <td></td>
        </c:forEach>
        <td></td>
        <td></td>
        <c:if test="${filter == '조류'}"><td></td></c:if>
      </tr>
      <c:forEach var="item" items="${result.get(family).get(order)}" varStatus="i">
        <tr class="f-o-item">
          <td class="scName">
            <c:forEach var="sc" items="${item.getScientificNameArray()}" varStatus="idx">
                <span  <c:if test="${idx.index == 0 || idx.index == 1 ||
                 (idx.index > 2 && (item.getScientificNameArray()[idx.index - 1] == 'var.') ||
                 item.getScientificNameArray()[idx.index - 1] == 'for.' ||
                 item.getScientificNameArray()[idx.index - 1] == 'subsp.')}">class="font-italic"</c:if>  >
                    ${sc}
                </span>
            </c:forEach>
          </td>
          <td>${item.realName}</td>
          <c:forEach var="name" items="${group_name}" varStatus="i">
            <td class="text-center"><c:if test="${item.getGroup().contains(name)}">O</c:if></td>
          </c:forEach>
          <td class="text-center">O</td>
          <c:if test="${filter == '조류'}"><td class="text-center">${item.currentStatus}</td></c:if>
          <td class="text-center">
            <c:if test="${filter =='담수어류'}">
              <c:forEach var="etc" items="${item.getETCList()}" varStatus="ie">
                ${etc} <c:if test="${ ie.index+1 != item.getETCList().size()}">,</c:if>
              </c:forEach>
            </c:if>
            <c:if test="${filter !='담수어류'}">${item.getETC()}</c:if>
          </td>
        </tr>
      </c:forEach>
    </c:forEach>
  </c:forEach>
  <tr>
    <td class="text-center" colspan="2">총 합</td>
    <c:forEach var="name" items="${group_name}" varStatus="i">
      <td class="text-center">${totalMap.get(name).getTotal()}</td>
    </c:forEach>
    <td class="text-center">${totalMap.get('totalKSH').getTotal()}</td>
    <td class="text-center" colspan="3"></td>
  </tr>
  </tbody>
</table>
</body>
</html>
