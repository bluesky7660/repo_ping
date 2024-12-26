//정규식
var idRegExp = /^[a-zA-Z]{1}[a-zA-Z0-9]{4,14}$/;
var passwordRegExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
var krAlphaNumRegExp = /^[ㄱ-ㅎ가-힣A-Za-z0-9]+$/;
var krNameRegExp = /^[가-힣]{2,4}$/;
var alphaNumRegExp = /^[a-zA-Z0-9]+$/;
var nonkrRegExp=/^[a-zA-Z0-9!@#$%^&*()_+={}\[\]:;"'<>,.?/~`-]*$/;

var numericRegExp = /^[0-9]+$/;
var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
var birthRegExp =  /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
var phoneRegExp = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;

//정규식text
const codeRegExpText = "이름은 한글, 영대소문자, 숫자만 입력해 주세요";
const inputNullText = "내용을 적어주세요.";
const selectNullText = "내용을 선택해주세요.";
const alphaNumRegExpText = "영대소문자, 숫자만 입력해 주세요";
const numRegExpText = "정수형 숫자만 입력해 주세요";
const idRegExpText = "아이디는 첫글자는 영문자로 시작하며, 5~15자의 영대소문자와 숫자만 포함해야 합니다.";
const pwRegExpText = "비밀번호는 8~15자 사이여야 하며, 최소 1개의 숫자, 영문자, 특수문자를 포함해야 합니다.";
const pwCkRegExpText = "비밀번호가 일치하지 않습니다."
const emailRegExpText = "이메일 형식에 따라 정확히 입력해주세요";
const telRegExpText = "정확한 핸드폰번호를 입력해주세요: - 제외";
const birthRegExpText = "정확한 생년월일 8자리를 입력해주세요";
const nameRegExpText = "한글만 입력해주세요.";
const genderRegExpText = "성별을 선택해주세요.";

//밸리데이션
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
            
            //정규식
            //https://choijying21.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EC%9E%90%EC%A3%BC-%EC%93%B0%EB%8A%94-%EC%A0%95%EA%B7%9C%EC%8B%9D-%EB%AA%A8%EC%9D%8C-%EC%9D%B4%EB%A9%94%EC%9D%BC-%ED%95%B8%EB%93%9C%ED%8F%B0-%EC%A3%BC%EB%AF%BC%EB%B2%88%ED%98%B8-%EB%93%B1

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
            
    //출처: https://choijying21.tistory.com/entry/자바스크립트-자주-쓰는-정규식-모음-이메일-핸드폰-주민번호-등 [JDevelog:티스토리]
            console.log("생일: "+objValue);
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
                var text = "아이디는 첫글자는 영문자로 시작하며, 5~15자의 영대소문자와 숫자만 포함해야 합니다.";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }
        else if (element.classList.contains('valid-password')) {
            console.log("비밀번호형식");

            if(!passwordRegExp.test(objValue)){
                var text = "비밀번호는 8~15자 사이여야 하며, 최소 1개의 숫자, 영문자, 특수문자를 포함해야 합니다.";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                
                return true;
            }
        }
        else if (element.classList.contains('valid-passwordCk')) {
            console.log("비밀번호체크형식");
            const pw = document.querySelector(".valid-password");
            if(!(pw.value == objValue)){
                feedback.textContent = pwCkRegExpText;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                
                return true;
            }
        }else if (element.classList.contains('valid-userPasswordCk')) {
            console.log("현재비밀번호형식");
            if(!passwordRegExp.test(objValue)){
                var text = "현재 비밀번호는 8~15자 사이여야 하며, 최소 1개의 숫자, 영문자, 특수문자를 포함해야 합니다.";
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
        }else if (element.classList.contains('valid-nonkr')) {
            console.log("한글만뺀");
            
            if(!nonkrRegExp.test(objValue)){
                var text = "영대소문자, 숫자, 특수문자 입력해주세요";
                feedback.textContent = text;
                element.focus();
                return false;
            } else {
    // 	    	by pass
                return true;
            }
        }
        else{
            console.log("else 통과");
            return true;
        }
}
window.addEventListener('load', function() {
    
    //로그인 
    const loginBtn = document.getElementById("loginBtn");
    if(loginBtn){
        const userId = document.getElementById("login_email");
        const userPassword = document.getElementById("login_password");
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
                feedback.textContent = "이메일을 입력해 주세요.";
                userId.classList.add('is-invalid');
                feedbackChk.classList.add('is-invalid');
                idValid = false;
            } else {
                
                if(!emailRegExp.test(idValue)){
                    userId.classList.add('is-invalid');
                    userPassword.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    feedback.textContent = "이메일 또는 비밀번호가 잘못 되었습니다. 이메일과 비밀번호를 정확히 입력해 주세요.";
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
				url : "/loginUsrProc", 
				data : {
					"mmEmail" : $("#login_email").val()
					,"mmPasswd" : $("#login_password").val() 
					// ,"autoLogin" : $("#rememberPassword").is(":checked")
				},
				success : function(response) {
					if (response.rt === "success") {
						console.log("로그인 성공");
						window.location.href = response.redirectUrl;
                    } else {
                        userId.classList.add('is-invalid');
                        userPassword.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                        feedback.textContent = "이메일 또는 비밀번호가 잘못 되었습니다. 이메일과 비밀번호를 정확히 입력해 주세요.";
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
                    url : "/loginUsrProc", 
                    data : {
                        "mmEmail" : $("#login_email").val()
					    ,"mmPasswd" : $("#login_password").val() 
                        // ,"autoLogin" : $("#rememberPassword").is(":checked")
                    },
                    success : function(response) {
                        if (response.rt === "success") {
                            console.log("로그인 성공");
                            window.location.href = response.redirectUrl;
                        } else {
                            userId.classList.add('is-invalid');
                            userPassword.classList.add('is-invalid');
                            feedbackChk.classList.add('is-invalid');
                            feedback.textContent = "이메일 또는 비밀번호가 잘못 되었습니다. 이메일과 비밀번호를 정확히 입력해 주세요.";
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

    //로그아웃
    const logoutBtn = document.querySelectorAll(".logoutBtn");
    console.log("logoutBtn: "+logoutBtn);
    if (logoutBtn) {
        logoutBtn.forEach(element => {
            element.onclick = function (){
                //ajax 로그아웃
                $.ajax({
                    async: true 
                    ,cache: false
                    ,type: "post"
                    ,url: "/logoutUsrProc"
                    ,success: function(response) {
                        location.href = "/v1/index";
                    }
                    ,error : function(jqXHR, textStatus, errorThrown){
                        alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                    }
                });
            }
        });
    }

    //회원가입
    const signupBtn = document.getElementById("signupBtn");
    if (signupBtn) {
        signupBtn.onclick = function (){
            
            var objs = document.querySelectorAll(".validate-this");
            var i= 0;
            var validateChk = [];
            for(let element of objs){
                var objValue = element.value.trim();
                // const elementBox = element.parentElement;
                let elementBox;
                if(element.closest("td")){
                    elementBox = element.closest("td");
                }else if (element.closest("li")) {
                    elementBox = element.closest("li");
                }else if (element.closest(".register_item")) {
                    elementBox = element.closest(".register_item");
                }
                const invalidBoxChk = elementBox.querySelector(".invalid-box");
                if(invalidBoxChk){
                    elementBox.querySelector(".invalid-box").remove();
                }
                // console.log("elementBox:" +elementBox.outerHTML);

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
                if (objValue == "" || objValue == null) {
                    if(element.tagName ==='INPUT'){
                        if(element.classList.contains('valid-birth-num')){
                            feedback.textContent = birthRegExpText;
                        }else{
                            feedback.textContent = inputNullText;
                        }
                    }else if(element.tagName ==='SELECT'){
                        if(element.classList.contains('valid-gender')){
                            feedback.textContent = genderRegExpText;
                        }else{
                            feedback.textContent = selectNullText;
                        }
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
                        element.classList.remove('is-invalid');
                        element.classList.add('is-valid');
                        feedbackChk.classList.remove('is-invalid');
                    }else{
                        if(i==0){
                            element.focus();
                        }
                        
                        validateChk[i] = false;
                        i++;
                        element.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                    }
                    
                }
            };
            console.log("validateChk: "+validateChk);
            if(validateChk.includes(false)){
                // alert("검사실패");
                return false;
            }

            //ajax 
            $.ajax({
                async: true 
                ,cache: false
                ,type: "post"
                /* ,dataType:"json" */
                ,url: "/registerInst"
                ,data : { 
                    "mmEmail" : $("#register_email").val().trim(),
                    "mmName" : $("#register_name").val().trim(),
                    "mmPasswd" : $("#register_passwd").val(),
                    "mmTel" : $("#register_tel").val().trim(),
                    "mmGender" : $("#register_gender").val().trim(),
                    "mmBirthDay" : $("#register_birthDay").val().trim(),
                    "mmAdminNy": 0,
                }//, "autoLogin" : $("#autoLogin").is(":checked")}
                ,success: function(response) {
                    if(response.rt == "success") {
                        window.location.href = "/v1/login";
                        
                    } else {

                    }
                }
                ,error : function(jqXHR, textStatus, errorThrown){
                    alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                }
            });
            
        }
    }
    
    //계절별 어종
    $('#seasonalTab .nav-link').on('click', function(e) {
        const season = $(this).data('season');
        const tabContent = document.getElementById('season_pointList');
        const thisPage = 1;
        console.log("season:",season);
        $.ajax({
            url: '/v1/mapPoint/getSeasonalData', 
            method: 'post',
            contentType: 'application/json', 
            data: JSON.stringify({ shSeason: season, thisPage: thisPage }),
            success: function(response) {
                console.log("tabContent:",tabContent);
                tabContent.innerHTML="";
                console.log("tabContent 2:",tabContent);
                console.log("tabContent.innerHTML:",tabContent.innerHTML);
                response.data.forEach(function(point) {
                    console.log("point: ",point);
                    const htmlContent = `
                        <div class="col-sm-6 col-lg-3">
                            <div class="card ts-item ts-card">
                                <a href="/v1/mapPoint/mapPointDetail?mpSeq=${point.mpSeq}">
                                    <div class="card-img ts-item__image" data-bg-image="${point.path != null ? point.path : '/usr/v1/template/themeforest-v1.0/assets/img/FishOn_default.png'}" 
                                    style="background-image: url(&quot;${point.path != null ? point.path : '/usr/v1/template/themeforest-v1.0/assets/img/FishOn_default.png'}&quot;);">
                                        <figure class="ts-item__info">
                                            <h4>${point.mpTitle}</h4>
                                            <aside>
                                                <i class="fa fa-map-marker mr-2"></i>
                                                <span>${point.mpAddress}</span>
                                            </aside>
                                        </figure>
                                    </div>
                                    <div class="card-body">
                                        <div class="ts-description-lists">
                                            <dl>
                                                <dt>생성일</dt>
                                                <dd>${point.mpRegDate}</dd>
                                            </dl>
                                            <dl>
                                                <dt>${point.speciesSeason != null ? point.speciesSeason + ' 시즌 어종' : '어종'}</dt>
                                                <dd>${point.fsNameList}</dd>
                                            </dl>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <span class="ts-btn-arrow">자세히 보기</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    `;
                    tabContent.innerHTML += htmlContent;
                });
                console.log("계절 탭 동작성공");
                console.log("response.thisPage:",response.thisPage);
                console.log("response.totalPages:",response.totalPages);
                const paginationContainer = document.querySelector('#season_point #pagination-items .pagination');
                const index = '';
                updatePagination(response.thisPage, response.totalPages,paginationContainer ,index);
            },
            error: function() {
                alert('데이터를 불러오는 데 실패했습니다.');
            }
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
                    alert(`You can only select up to ${maxSelection} options.`);
                    checkbox.checked = false; // 선택을 취소
                }
            });
        });
    }
    
});

/*페이지 네이션*/
function updatePagination(thisPage, totalPages, paginationContainer,index) {
    // const paginationContainer = document.querySelector("#pagination-items .pagination");
    console.log()
    paginationContainer.innerHTML = ""; // 기존 페이지네이션 초기화

    const pageNumToShow = 5; // 보여줄 페이지 번호 수
    const startPage = Math.max(1, thisPage - Math.floor(pageNumToShow / 2));
    const endPage = Math.min(totalPages, startPage + pageNumToShow - 1);

    // 이전 페이지로 이동 버튼
    if (thisPage > 1) {
        const prevButton = document.createElement("li");
        prevButton.className = "page-item";
        prevButton.innerHTML = `
            <a class="page-link" href="javascript:void(0)" onclick="goList${index}(${thisPage - 1})">
                <i class="fa fa-caret-left"></i>
            </a>`;
        paginationContainer.appendChild(prevButton);
    }

    // 페이지 번호 버튼
    for (let page = startPage; page <= endPage; page++) {
        const pageItem = document.createElement("li");
        pageItem.className = `page-item ${page === thisPage ? "active" : ""}`;
        pageItem.innerHTML = `
            <a class="page-link" href="javascript:void(0)" onclick="goList${index}(${page})">${page}</a>`;
        paginationContainer.appendChild(pageItem);
    }

    // 다음 페이지로 이동 버튼
    if (thisPage < totalPages) {
        const nextButton = document.createElement("li");
        nextButton.className = "page-item";
        nextButton.innerHTML = `
            <a class="page-link" href="javascript:void(0)" onclick="goList${index}(${thisPage + 1})">
                <i class="fa fa-caret-right"></i>
            </a>`;
        paginationContainer.appendChild(nextButton);
    }
}
