<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>종목록관리</title>
    <script type="text/javascript">

      $(function () {

        $("#button").click(function (e) {
          var f = document.Form;
          var data = $(f).serialize();
          l_ajax("post", "json", "/manage/edit", data, function (result) {
            if(result.content == 'OK')
              swal("저장이 완료되었습니다.")
            else
              swal("데이터에 문제가 있습니다.")
          });

        });
      });

    </script>
</head>
<body>
<div>

    <!-- Page Header-->
    <header class="page-header">
        <div class="container-fluid">
            <h2 class="no-margin-bottom">종목록관리 - ${model.scientificName} ${model.realName}</h2>
        </div>
    </header>

    <section class="forms">
        <div class="container-fluid">
            <div class="row">
                <!-- Form Elements -->
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <form class="form-horizontal" name="Form" method="post">
                                <input type="hidden" name="filter" value="${filter}">
                                <c:if test="${filter == '저서동물' || filter == '관속식물'}">
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">문(한글)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="phylumName" value="${model.phylumName}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">문(영어)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="phylumEnName" value="${model.phylumEnName}">
                                    </div>
                                </div>
                                <div class="line"></div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">강(한글)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="className" value="${model.className}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">강(영어)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="classEnName" value="${model.classEnName}">
                                    </div>
                                </div>
                                <div class="line"></div>
                                </c:if>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">목(한글)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="orderName" value="${model.orderName}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">목(영어)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="orderEnName" value="${model.orderEnName}">
                                    </div>
                                </div>
                                <div class="line"></div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">과(한글)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="familyName" value="${model.familyName}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">과(영어)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="familyEnName" value="${model.familyEnName}">
                                    </div>
                                </div>
                                <div class="line"></div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">학명</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="scientificName" value="${model.scientificName}" readonly>
                                        <small class="form-text">수정이 불가합니다.</small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">이름</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="realName" value="${model.realName}">
                                    </div>
                                </div>
                                <div class="line"></div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">멸종위기</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="propCrisis" value="${model.propCrisis}">
                                    </div>
                                </div>
                                <div class="line"></div>
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label">희귀종</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="propRare" value="${model.propRare}">
                                    </div>
                                </div>
                                <c:if test="${filter != '저서동물' && filter != '관속식물'}">
                                    <div class="line"></div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 form-control-label">천연기념물</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="propMonument" value="${model.propMonument}">
                                        </div>
                                    </div>
                                    <div class="line"></div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 form-control-label">외래종</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="propAlien" value="${model.propAlien}">
                                        </div>
                                    </div>
                                    <div class="line"></div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 form-control-label">생태계교란생물</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="propEcosystem" value="${model.propEcosystem}">
                                        </div>
                                    </div>
                                </c:if>

                                <div class="line"></div>
                                <div class="form-group row">
                                    <div class="col-sm-4 offset-sm-3">
                                        <a id="button" class="btn btn-primary">Save changes</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


</div>
</body>
</html>