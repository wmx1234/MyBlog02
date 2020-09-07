'use strict';
let searchFunc = function (path, search_id, content_id) {

    let $input = document.getElementById(search_id);

    let $resultContent = document.getElementById(content_id);

    $input.addEventListener('input', function () {

        let keywords = this.value.trim().toLowerCase().split(/[\s\-]+/);

        $resultContent.innerHTML = "";

        if (this.value.trim().length <= 0) {
            return;
        }

        $.get("http://localhost:8080/articles", {name: keywords}).done(function(e){
            let str = '<ul class=\"search-result-list\">';
            let datas = e.content;

            // perform local searching
            datas.forEach(function (data) {

                let isMatch = true;
                let content_index = [];
                let data_title = data.articleTitle.trim().toLowerCase();
                let data_content = data.articleContent.trim().replace(/<[^>]+>/g, "").toLowerCase();
                let data_url = data.articleUrl;
                let index_title = -1;
                let index_content = -1;
                let first_occur = -1;
                // only match artiles with not empty titles and contents
                if (data_title != '' && data_content != '') {
                    keywords.forEach(function (keyword, i) {
                        index_title = data_title.indexOf(keyword);
                        index_content = data_content.indexOf(keyword);
                        if (index_title < 0 && index_content < 0) {
                            isMatch = false;
                        } else {
                            if (index_content < 0) {
                                index_content = 0;
                            }
                            if (i == 0) {
                                first_occur = index_content;
                            }
                        }
                    });
                }
                // show search results
                if (isMatch) {
                    str += "<li><a href='" + data_url + "' class='search-result-title'>" + data_title + "</a>";
                    let content = data.articleContent.trim().replace(/<[^>]+>/g, "");
                    if (first_occur >= 0) {
                        // cut out 100 characters
                        let start = first_occur - 20;
                        let end = first_occur + 80;
                        if (start < 0) {
                            start = 0;
                        }
                        if (start == 0) {
                            end = 100;
                        }
                        if (end > content.length) {
                            end = content.length;
                        }
                        let match_content = content.substr(start, end);
                        // highlight all keywords
                        keywords.forEach(function (keyword) {
                            let regS = new RegExp(keyword, "gi");
                            match_content = match_content.replace(regS, "<em class=\"search-keyword\">" + keyword + "</em>");
                        });

                        str += "<p class=\"search-result\">" + match_content + "...</p>"
                    }
                    str += "</li>";
                }
            });
            str += "</ul>";
            $resultContent.innerHTML = str;
        });


    });

}