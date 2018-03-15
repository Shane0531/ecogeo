var ERRCODE = {
  LOGINAUTH : 1,
  NETWORK : 2,
  FAIL : 3,
  READ : 4,
  ACCESS : 5,
  UNKNOWN : 99
};

function l_alertMessage(code) {
  switch(code) {
    case ERRCODE.LOGINAUTH :
      swal("로그인 후 이용 가능합니다.");
      break;
    case ERRCODE.NETWORK :
      swal("Network Error.\n잠시 후 다시 시도해 주십시요.");
      break;
    case ERRCODE.UNKNOWN :
      swal("알수없는 에러가 발생하였습니다.\n잠시 후 다시 시도해 주십시요.");
      break;
    case ERRCODE.FAIL :
      swal("실패하였습니다. 다시 시도해 주십시요.");
      break;
    case ERRCODE.READ :
      swal("해당 컨텐츠를 조회 할 수 없습니다.");
      break;
    case ERRCODE.ACCESS :
      swal("접근 권한이 없습니다.");
      break;
    default:
      swal("알수없는 에러가 발생하였습니다.\n잠시 후 다시 시도해 주십시요.");
  }
}

// Ajax
function l_ajax(type, dataType, url, data, callback, error) {
  try {
    $.ajax({
      cache: false, type: type, dataType: dataType,
      url: url, data : data,
      success : function(result) {
        callback(result);
      },
      error: function(xhr, textStatus, errorThrown) {
        l_alertMessage(ERRCODE.NETWORK);
        if( error != null ) { error(); }
      }
    });
  } catch(e) {
    l_alertMessage(ERRCODE.UNKNOWN);
    if( error != null ) { error(); }
  }
}