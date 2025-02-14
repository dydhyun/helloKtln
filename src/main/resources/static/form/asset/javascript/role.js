async function helloName(role) {
    let resultDiv = document.getElementById("userInfoDiv");

    try {
        let response = await fetch("api/role/" + role, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            throw new Error("서버 응답 오류");
        }

        let data = await response.json();
        let helloMessage = data.message; // 문자열로 직접 접근
        let user = data.user; // 객체로 직접 접근

        console.log(helloMessage)
        console.log(user);

        if (user) {
            resultDiv.innerHTML =
                `<div class="search-result-form">
                    <h1>${helloMessage}</h1>
                    <li>id = ${user.id}</li>
                    <li>name = ${user.name}</li>
                    <li>email = ${user.email}</li>
                </div>`;
        } else {
            resultDiv.innerHTML = "<h3>Fail</h3>";
        }

    } catch (error) {
        console.error("요청 실패:", error);
        resultDiv.innerHTML = "<h3>message load fail</h3>";
    }
}
