async function searchBlog(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    let query = document.getElementById("query").value;
    let resultDiv = document.getElementById("searchResult");

    if (!query.trim()) {
        alert("검색어를 입력하세요!");
        return;
    }

    try {
        let response = await fetch("api/blog/search", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(query)
        });

        if (!response.ok) {
            throw new Error("서버 응답 오류");
        }

        let data = await response.json(); // JSON 변환
        let searchResults = JSON.parse(data.searchResults); // JSON 객체 변환

        console.log(searchResults.documents); // ✅
        console.log(searchResults.documents[0].blogname);

        if (searchResults.documents.length > 0) {
            resultDiv.innerHTML = searchResults.documents.map(doc =>
                `<div class="search-result-form">
                    <li><a href="${doc.url}">${doc.title}</a></li>
                    ${doc.contents}
                    <img src="${doc.thumbnail}">
                </div>`
            ).join('');
        } else {
            resultDiv.innerHTML = "<h3>검색 결과 없음</h3>";
        }

    } catch (error) {
        console.error("검색 요청 실패:", error);
        resultDiv.innerHTML = "<h3>검색 실패</h3>";
    }
}
