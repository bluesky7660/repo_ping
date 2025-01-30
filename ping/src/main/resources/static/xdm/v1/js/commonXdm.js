
window.addEventListener('load', function() {
    const form = document.querySelector("form");
    var formUrl;
    if(form){
        formUrl = form.action;
    }

    //밸리데이션 변수
    const codeNullText = "코드이름을 적어주세요.";
    const codeRegExpText = "이름은 한글, 영대소문자, 숫자만 입력해 주세요";
    const inputNullText = "내용을 적어주세요.";
    const checkboxNullText = "한개 이상 선택해주세요.";
    const selectNullText = "내용을 선택해주세요.";
    const alphaNumRegExpText = "영대소문자, 숫자만 입력해 주세요";
    const orderNullText = "순서를 적어주세요.";
    const numNullText = "숫자를 적어주세요.";
    const numRegExpText = "정수형 숫자만 입력해 주세요";
    const userId = document.getElementById("userId");
    const userPassword = document.getElementById("userPassword");
    var feedbackText = [];
    const feedbackList = document.querySelectorAll(".invalid-feedback");
    feedbackList.forEach(element => {
        feedbackText.push(element.textContent.trim());
    });

    //정규식
    var idRegExp = /^[a-zA-Z0-9]{5,15}$/;
    var passwordRegExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    var krAlphaNumRegExp = /^[ㄱ-ㅎ가-힣A-Za-z0-9\s]+$/;
    var krNameRegExp = /^[가-힣]{2,4}$/;
    var alphaNumRegExp = /^[a-zA-Z0-9]+$/;
    var numericRegExp = /^[0-9]+$/;
    var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    var birthRegExp =  /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    var dateTimeRegExp =  /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (\d{2}):([0-5][0-9])$/;
    var phoneRegExp = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
    
    const loginBtn = document.querySelector("#loginBtn");
    if(loginBtn){
        loginBtn.addEventListener("click", function() {
            // const loginBoxParent = element.closest('.login-box');
            
            const feedbackChk = document.querySelector(".invalid-child");
            const feedback = document.querySelector(".invalid-feedback");
            
            let idValid = true;
            let pwValid = true;
            let idValue = userId.value.trim();
            let PasswordValue = userPassword.value.trim();
    
            // 아이디 필드 체크
            if (idValue == "" || idValue == null) {
                feedback.textContent = "아이디를 입력해 주세요.";
                userId.classList.add('is-invalid');
                feedbackChk.classList.add('is-invalid');
                idValid = false;
            } else {
                
                if(!emailRegExp.test(idValue)){
                    userId.classList.add('is-invalid');
                    userPassword.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    feedback.textContent = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                    idValid = false; 
                    return false;
                }else{
                    userId.classList.remove('is-invalid');
                    feedbackChk.classList.remove('is-invalid');
                    // userId.classList.add('is-valid');
                }
                
            }
    
            // 비밀번호 필드 체크
            if (PasswordValue == "" || PasswordValue == null) {
                feedback.textContent = "비밀번호를 입력해 주세요.";
                userPassword.classList.add('is-invalid');
                feedbackChk.classList.add('is-invalid');
                pwValid = false;
            } else {
                
                // if(!passwordRegExp.test(PasswordValue)){
                //     userId.classList.add('is-invalid');
                //     userPassword.classList.add('is-invalid');
                //     feedback.textContent = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                //     pwValid = false;
                //     return false;
                // }else{
                //     userPassword.classList.remove('is-invalid');
                //     userPassword.classList.add('is-valid');
                // }
                userPassword.classList.remove('is-invalid');
                feedbackChk.classList.remove('is-invalid');
                // userPassword.classList.add('is-valid');
            }
    
            // 아이디와 비밀번호 모두 입력하지 않은 경우 처리
            if (!idValid && !pwValid) {
                feedback.textContent = "아이디와 비밀번호를 입력해 주세요.";
            }
    
            // 둘 다 중에 하나라도 유효하지 않으면 submit 방지
            if (!idValid || !pwValid) {
                feedbackChk.classList.add('is-invalid');
                return false;
            }
    
            feedback.textContent = "";
    
            //ajax 로그인
            $.ajax({
				async : true, 
				cache : false, 
				type : "post", 
				url : "/v1/infra/loginXdmProc", 
				data : {
					"mmEmail" : $("#userId").val()
					,"mmPasswd" : $("#userPassword").val() 
					,"autoLogin" : $("#rememberPassword").is(":checked")
				},
				success : function(response) {
					if (response.rt === "success") {
						console.log("로그인 성공");
						location.href = "/v1/dashBoardXdm";
					} else {
						$("#modalAlertTitle").text("다시 확인해주세요");
						$("#modalAlertBody").text(
								"ID / 비밀번호를 잘못 입력하셨거나 등록되지 않은 계정입니다.");
						$("#modalAlert").modal("show");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.error("AJAX 요청 오류:", textStatus, errorThrown); 
				}
			});

        })
    }
    if(document.querySelector(".login-container form")){
        document.querySelector(".login-container form").addEventListener("keydown", function(event) {
            // const loginBoxParent = element.closest('.login-box');
            if (event.key === "Enter") {
                const feedbackChk = document.querySelector(".invalid-child");
                const feedback = document.querySelector(".invalid-feedback");
                
                let idValid = true;
                let pwValid = true;
                let idValue = userId.value.trim();
                let PasswordValue = userPassword.value.trim();
        
                // 아이디 필드 체크
                if (idValue == "" || idValue == null) {
                    feedback.textContent = "아이디를 입력해 주세요.";
                    userId.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    idValid = false;
                } else {
                    
                    if(!emailRegExp.test(idValue)){
                        userId.classList.add('is-invalid');
                        userPassword.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                        feedback.textContent = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                        idValid = false; 
                        return false;
                    }else{
                        userId.classList.remove('is-invalid');
                        feedbackChk.classList.remove('is-invalid');
                        // userId.classList.add('is-valid');
                    }
                    
                }
        
                // 비밀번호 필드 체크
                if (PasswordValue == "" || PasswordValue == null) {
                    feedback.textContent = "비밀번호를 입력해 주세요.";
                    userPassword.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    pwValid = false;
                } else {
                    
                    if(!passwordRegExp.test(PasswordValue)){
                        userId.classList.add('is-invalid');
                        userPassword.classList.add('is-invalid');
                        feedback.textContent = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                        pwValid = false;
                        return false;
                    }else{
                        userPassword.classList.remove('is-invalid');
                        userPassword.classList.add('is-valid');
                    }
                    userPassword.classList.remove('is-invalid');
                    feedbackChk.classList.remove('is-invalid');
                    // userPassword.classList.add('is-valid');
                }
        
                // 아이디와 비밀번호 모두 입력하지 않은 경우 처리
                if (!idValid && !pwValid) {
                    feedback.textContent = "아이디와 비밀번호를 입력해 주세요.";
                }
        
                // 둘 다 중에 하나라도 유효하지 않으면 submit 방지
                if (!idValid || !pwValid) {
                    feedbackChk.classList.add('is-invalid');
                    return false;
                }
        
                feedback.textContent = "";
        
                //ajax 로그인
                $.ajax({
                    async : true, 
                    cache : false, 
                    type : "post", 
                    url : "/v1/infra/loginXdmProc", 
                    data : {
                        "mmEmail" : $("#userId").val()
                        ,"mmPasswd" : $("#userPassword").val() 
                        ,"autoLogin" : $("#rememberPassword").is(":checked")
                    },
                    success : function(response) {
                        if (response.rt === "success") {
                            console.log("로그인 성공");
                            location.href = "/v1/dashBoardXdm";
                        } else {
                            $("#modalAlertTitle").text("다시 확인해주세요");
                            $("#modalAlertBody").text(
                                    "ID / 비밀번호를 잘못 입력하셨거나 등록되지 않은 계정입니다.");
                            $("#modalAlert").modal("show");
                        }
                    },
                    error : function(jqXHR, textStatus, errorThrown) {
                        console.error("AJAX 요청 오류:", textStatus, errorThrown); 
                    }
                });
            }
        })
    }
    
    //데이터삭제 js
    if(document.getElementById("ueleteBtn")||document.getElementById("deleteBtn")){
        var currentAction = form.action;
        var newAction = currentAction.slice(0, -4);
        function del() {
            form.action = newAction + 'Delt';	
            form.submit();
        }
        function uel() {
            form.action = newAction + 'Uelt';
            form.submit();
        }
        document.getElementById("deleteBtn").onclick = del;
        document.getElementById("ueleteBtn").onclick = uel;
    }
    //제출버튼
    const btnSubmit = document.getElementById("btnSubmit");
    if (btnSubmit) {
        btnSubmit.onclick = function (){
            var objs = document.querySelectorAll(".validate-this");
            var i= 0;
            let j= 0;
            // var validateChk = false;
            var validateChk = [];
            var checkboxSelected = [];
            for(let element of objs){
                var objValue = element.value.trim();
                // const feedback = element.parentElement.querySelector(".invalid-feedback");
                let elementBox;
                if(element.closest("td")){
                    elementBox = element.closest("td");
                }else if (element.closest("li")) {
                    elementBox = element.closest("li");
                }else if (element.closest(".required")) {
                    elementBox = element.closest(".required");
                }
                if(document.querySelector(".checkboxArea")){
                    elementBox = document.querySelector(".checkboxArea").closest(".required");
                }
                if(elementBox){
                    const invalidBoxChk = elementBox.querySelector(".invalid-box");
                    if(invalidBoxChk){
                        elementBox.querySelector(".invalid-box").remove();
                    }
                }
                // if(elementBox.querySelector(".invalid-box")){
                    
                // }
                
                if(elementBox.querySelector("div:not(.invalid-box)")&&elementBox.querySelector("div:not(.invalid-box)").classList.contains("mb-3")){
                    elementBox.querySelector("div:not(.invalid-box)").classList.remove("mb-3");
                }
                console.log("elementBox:",elementBox);
                const feedbackBox = document.createElement("div");
                const feedbackChild = document.createElement("div");
                const feedbackinner = document.createElement("div");
                feedbackBox.className  = "invalid-box mb-3";
                feedbackChild.className  = "invalid-child";
                feedbackinner.className  = "invalid-feedback";
                feedbackinner.id  = "invalid-feedback_"+i;
                feedbackBox.appendChild(feedbackChild);
                feedbackBox.appendChild(feedbackinner);
                elementBox.appendChild(feedbackBox);
                
                const feedbackChk = elementBox.querySelector(".invalid-child");
                const feedback = elementBox.querySelector(".invalid-feedback");
                console.log("objValue:",objValue);
                if (element.tagName === 'INPUT' && element.type === 'checkbox') {
                    if (!element.checked) {
                        checkboxSelected[j] = false;
                        j++;
                    } else {
                        checkboxSelected[j] = true;
                        j++;
                    }
                }
                if(!(element.type === "checkbox")){
                    if (objValue == "" || objValue == null) {
                        // var waring = feedback.textContent.trim();
                        if(element.tagName ==='INPUT'){
                            // if(element.type =="checkbox"){
                            //     console.log("checkbox:",objValue);
                            //     console.log("objValue:",objValue);
                            //     feedback.textContent = checkboxNullText;
                            // }else{
                            //     console.log("테스트:",inputNullText);
                            //     console.log("feedback:"+feedback);
                            //     feedback.textContent = inputNullText;
                            // }
                            console.log("테스트:",inputNullText);
                            console.log("feedback:"+feedback);
                            feedback.textContent = inputNullText;
                        }else if(element.tagName ==='SELECT'){
                            console.log("SELECT:"+feedback);
                            feedback.textContent = selectNullText;
                            // alert(selectNullText);
                        }
                        
                        if(i==0){
                            element.focus();
                        }
                        validateChk[i] = false;
                        i++;
                        element.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                    } else {
                        //by pass
                        var val  = RegExps(element,objValue,feedback);
                        console.log("체크");
                        if(val == true){
                            validateChk[i] = true;
                            i++;
                            if(!(element.type =="checkbox")){
                                element.classList.add('is-valid');
                            }
                            element.classList.remove('is-invalid');
                            feedbackChk.classList.remove('is-invalid');
                            elementBox.querySelector("div:not(.invalid-box)").classList.add("mb-3");
                            elementBox.querySelector(".invalid-box").remove();
                        }else{
                            // feedback.textContent = codeRegExpText;
                            if(i==0){
                                element.focus();
                            }
                            validateChk[i] = false;
                            i++;
                            element.classList.add('is-invalid');
                            feedbackChk.classList.add('is-invalid');
                        }
                        
                    }
                }
            };
            if (checkboxSelected.length > 0&&!checkboxSelected.includes(true)) {
                alert("체크박스를 하나 이상 선택해주세요.");
                const elementBox = document.querySelector(".checkboxArea ").closest(".required");

                const checkboxes = elementBox.querySelectorAll('input[type="checkbox"]');
                for(let k = 0; k < checkboxes.length; k++){
                    checkboxes[k].classList.add('is-invalid');
                }

                elementBox.querySelector(".invalid-feedback").textContent = checkboxNullText;
                if(document.querySelector(".invalid-icon")){
                    document.querySelector(".invalid-icon").remove();
                }
                if(document.querySelector(".valid-icon")){
                    document.querySelector(".valid-icon").remove();
                }
                const formLabel = elementBox.querySelector(".form-label");
                const imageBox = document.createElement("span");
                imageBox.className = "invalid-icon";
                imageBox.style.marginLeft = "10px";
                imageBox.style.display = "inline-block";
                imageBox.style.width = "17px";
                imageBox.style.height = "17px";
                imageBox.style.backgroundImage = "url('data:image/svg+xml,%3csvg%20xmlns=%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20viewBox=%270%200%2012%2012%27%20width=%2712%27%20height=%2712%27%20fill=%27none%27%20stroke=%27%23f87957%27%3e%3ccircle%20cx=%276%27%20cy=%276%27%20r=%274.5%27/%3e%3cpath%20stroke-linejoin=%27round%27%20d=%27M5.8%203.6h.4L6%206.5z%27/%3e%3ccircle%20cx=%276%27%20cy=%278.2%27%20r=%27.6%27%20fill=%27%23f87957%27%20stroke=%27none%27/%3e%3c/svg%3e')";
                imageBox.style.backgroundRepeat = "no-repeat";
                imageBox.style.backgroundSize = "calc(0.75em + 0.375rem) calc(0.75em + 0.375rem)";
                formLabel.insertAdjacentElement('afterend', imageBox);
                validateChk[i] = false;
                return false;
            }else{
                if(document.querySelector(".checkboxArea ")){
                    const elementBox = document.querySelector(".checkboxArea ").closest(".required");

                    if(elementBox.querySelector(".invalid-icon")){
                        elementBox.querySelector(".invalid-icon").style.backgroundImage = "url('data:image/svg+xml,%3csvg%20xmlns=%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20viewBox=%270%200%208%208%27%3e%3cpath%20fill=%27%2326ba4f%27%20d=%27M2.3%206.73.6%204.53c-.4-1.04.46-1.4%201.1-.8l1.1%201.4%203.4-3.8c.6-.63%201.6-.27%201.2.7l-4%204.6c-.43.5-.8.4-1.1.1z%27/%3e%3c/svg%3e')";
                        elementBox.querySelector(".invalid-icon").className = "valid-icon";
                    }else{
                        const formLabel = elementBox.querySelector(".form-label");
                        const imageBox = document.createElement("span");
                        imageBox.className = "valid-icon";
                        imageBox.style.marginLeft = "10px";
                        imageBox.style.display = "inline-block";
                        imageBox.style.width = "17px";
                        imageBox.style.height = "17px";
                        imageBox.style.backgroundImage = "url('data:image/svg+xml,%3csvg%20xmlns=%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20viewBox=%270%200%208%208%27%3e%3cpath%20fill=%27%2326ba4f%27%20d=%27M2.3%206.73.6%204.53c-.4-1.04.46-1.4%201.1-.8l1.1%201.4%203.4-3.8c.6-.63%201.6-.27%201.2.7l-4%204.6c-.43.5-.8.4-1.1.1z%27/%3e%3c/svg%3e')";
                        imageBox.style.backgroundRepeat = "no-repeat";
                        imageBox.style.backgroundSize = "calc(0.75em + 0.375rem) calc(0.75em + 0.375rem)";
                        formLabel.insertAdjacentElement('afterend', imageBox);
                    }
                    
                    elementBox.querySelector(".invalid-child").classList.remove('is-invalid');
                    elementBox.querySelector("div:not(.invalid-box)").classList.add("mb-3");
                    if(elementBox.querySelector(".invalid-box")){
                        elementBox.querySelector(".invalid-box").remove();
                    }
                    const checkboxes = elementBox.querySelectorAll('input[type="checkbox"]');
                    for(let k = 0; k < checkboxes.length; k++){
                        checkboxes[k].classList.remove('is-invalid');
                    }
                    if(elementBox.querySelector(".valid-icon")){
                        document.querySelector(".valid-icon").remove();
                    }
                }
                if(document.querySelector(".invalid-icon")){
                    document.querySelector(".invalid-icon").remove();
                }
                
                validateChk[i] = true;
            }
            console.log("validateChk:",validateChk);
            if(validateChk.includes(false)){
                alert("검사실패");
                return false;
            }
            btnSubmit.closest("form").action = btnSubmit.closest("form").action;
            btnSubmit.closest("form").submit();

        }
    }

    //xdm로그아웃
    const logoutBtn = document.getElementById("logoutBtn");
    console.log("logoutBtn: "+logoutBtn);
    if (logoutBtn) {
        logoutBtn.onclick = function (){
            //ajax 로그아웃
            $.ajax({
                async : true, 
                cache : false, 
                type : "post", 
                url : "/v1/infra/logoutXdmProc", 
                success : function(response) {
                    if (response.rt === "success") { 
                        location.href = response.redirectUrl; 
                    } else {
                        alert("로그아웃 실패: " + response.message);
                    }
                },
                error : function(jqXHR, textStatus, errorThrown) {
                    console.error("AJAX Error: " + textStatus + " - "
                                    + errorThrown);
                }
            });
        }
    }
    
    function RegExps(element,objValue,feedback) {
        console.log("태그: "+element.id);
        console.log("클래스: "+element.classList);
        if (element.classList.contains('valid-korean-alpha-num')) {
            console.log("특문빼고");
            if(!krAlphaNumRegExp.test(objValue)){
                feedback.textContent = codeRegExpText;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        } else if (element.classList.contains('valid-alpha-num')) {
            console.log("한글빼고");
            if(!alphaNumRegExp.test(objValue)){
                feedback.textContent = alphaNumRegExpText;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        } else if (element.classList.contains('valid-numeric')) {
            console.log("숫자만");
            if(!numericRegExp.test(objValue)){
                feedback.textContent = numRegExpText;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }
        else if (element.classList.contains('valid-email')) {
            console.log("이메일만");
            if(!emailRegExp.test(objValue)){
                var text = "이메일 형식에 따라 정확히 입력해주세요";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        } else if (element.classList.contains('valid-birth-num')) {
            console.log("생년월일만");
            if(!birthRegExp.test(objValue)){
                var text = "정확한 생년월일 8자리를 입력해주세요";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }
        else if (element.classList.contains('valid-id')) {
            console.log("아이디형식");
            if(!idRegExp.test(objValue)){
                var text = "아이디는 5~15자의 영대소문자와 숫자만 포함해야 합니다.";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }else if (element.classList.contains('valid-user-name')) {
            console.log("한글만");
            if(!krNameRegExp.test(objValue)){
                var text = "최소 2자 이상, 최대 4자의 한글만 입력해주세요";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }
        else if (element.classList.contains('valid-phone-num')) {
            console.log("핸드폰번호 숫자만");
            if(!phoneRegExp.test(objValue)){
                var text = "정확한 핸드폰번호를 입력해주세요: - 제외";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }else if (element.classList.contains('valid-dateTime')) {
            console.log("년-월-일 시:분만 입력");
            if(!dateTimeRegExp.test(objValue)){
                var text = "정확한 년-월-일 시:분 형식 입력해주세요";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }else{
            console.log("else 통과");
            return true;
        }
    }

    const sidebarDropdown = document.querySelectorAll(".sidebar-dropdown > a");

    sidebarDropdown.forEach(function(link) {
        link.addEventListener("contextmenu", function(event) {
            event.preventDefault(); // 오른쪽 클릭 메뉴 방지
        });
    });

    /*체크 박스[어종 선택제한 최대 3개] */
    const checkboxes = document.querySelectorAll('input[name="fsSeqList"]');
    const maxSelection = 3;
    if(checkboxes){
        checkboxes.forEach((checkbox) => {
            checkbox.addEventListener("change", () => {
                const selectedCount = Array.from(checkboxes).filter((cb) => cb.checked).length;
    
                if (selectedCount > maxSelection) {
                    alert(`어종은 최대 ${maxSelection}종류 까지만 선택해주세요.`);
                    checkbox.checked = false; // 선택을 취소
                }
            });
        });
    }
});