<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>종목록생성기</title>
  <script type="text/javascript">

    $(function () {

      $("#button").click(function (e) {
        var f = document.Form;
        var url;
        $("#button").text(' ');
        $("#button").append("<img class='svg' src='assets/three-dots.svg' />");

        var filter = f.filter.value;
        if(filter === "관속식물")
          url = "species_result_plant.ajax"
        else if(filter === "저서동물")
          url = "species_result_ben.ajax"
        else if(filter === "포유류")
          url = "species_result_mam.ajax"
        else if(filter === "조류")
          url = "species_result_bird.ajax"
        else if(filter === "양서강")
          url = "species_result_amp.ajax"
        else if(filter === "파충강")
          url = "species_result_rep.ajax"
        else if(filter === "곤충")
          url = "species_result_insect.ajax"
        else
          url = "species_result_fish.ajax"

        var data = $(f).serialize();
        l_ajax("post", "html", url, data, function (html) {
          $("#result-area").empty().html(html);
          $( ".svg" ).remove();
          $("#button").text('가져오기');
        });

      });
    });

    function addNode() {
        $(".item-group-container").append("<div class='item-group has-shadow'>" +
            "<div class='input-group'>" +
            "<input type='text' name='group_name' class='form-control' placeholder='그룹이름' value=' '>" +
            "<div class='input-group-append'>" +
            "<button type='button' class='btn btn-danger' onclick='deleteItem(this)'>삭제</button></div></div>" +
            "<div class='item-group-textarea'><textarea class='form-control' rows='18' name='item_group'></textarea></div></div>"
        );
    }

    function deleteItem(obj) {
      swal({
          title: "Are you sure?",
          text: "정말로 그룹을 삭제하시겠습니까?",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "네",
          cancelButtonText: "아니요"
        },
        function (isConfirm) {
          if (isConfirm) {
            var item = obj.parentNode.parentNode.parentNode;
            item.remove();
          }
        });

    }
  </script>
</head>
<body>
<div>

  <header class="page-header">
    <div class="container-fluid">
      <h2 class="no-margin-bottom">종목록생성기</h2>
    </div>
  </header>
  <!-- Dashboard Counts Section-->
  <section class="dashboard-counts no-padding-bottom">
    <div class="container-fluid">
      <div class="row bg-white has-shadow no-padding-bottom">

        <form name="Form" method="post">
          <select name="filter" class="form-control" style="width: 150px; display: inline-block; height: 34px;">
            <option>관속식물</option>
            <option>포유류</option>
            <option>조류</option>
            <option>양서강</option>
            <option>파충강</option>
            <option>곤충</option>
            <option>담수어류</option>
            <option>저서동물</option>
          </select>
          <a class="btn btn-primary" onclick="addNode()">+ 그룹추가</a>

          <div class="item-group-container">

            <div class="item-group has-shadow">
              <div class="input-group">
                <input type="text" class="form-control" name='group_name' placeholder="그룹이름" value=" ">
                <div class="input-group-append">
                  <button type="button" class="btn btn-danger" onclick='deleteItem(this)'>삭제</button>
                </div>
              </div>
              <div class='item-group-textarea'><textarea class='form-control' rows='18' name='item_group'></textarea>
              </div>
            </div>

          </div>
        </form>

        <div class="item-fetch-container">
          <button class="btn btn-warning" id="button" style="font-weight: 400 !important;">가져오기</button>
        </div>
      </div>

    </div>

  </section>

  <section class="dashboard-counts">
    <div class="container-fluid">
      <div class="row bg-white has-shadow">

        <div id="result-area">

        </div>

      </div>

    </div>

  </section>


</div>
</body>
</html>