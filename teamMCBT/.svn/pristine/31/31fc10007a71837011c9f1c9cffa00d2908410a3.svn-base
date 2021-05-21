$.reload = function (page) {
    let requrl = "/board?";
    let category = $('select#category').val();
    let perPageNum = $('select#perPageNum').val();

    if (category !== '전체보기') {
        requrl += "category=" + category + "&";
    }
    if (perPageNum !== '10') {
        requrl += "perPageNum=" + perPageNum + "&";
    }
    if (typeof page !== "object") {
        requrl += "page=" + page;
    }
    location.href = requrl;
};

