window.addEventListener('load', function() {


    //정규식
    var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
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
    
    //계절별 어종
    $('#seasonalTab .nav-link').on('click', function(e) {
        const season = $(this).data('season');
        const tabContent = $('#season_pointList');
        $.ajax({
            url: '/v1/mapPoint/getSeasonalData', 
            method: 'post',
            data: { shSeason: season }, 
            success: function(response) {
                tabContent.empty();
                response.data.forEach(function(point) {
                     
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
                    tabContent.append(htmlContent);
                });
                console.log("계절 동작성공");
            },
            error: function() {
                alert('데이터를 불러오는 데 실패했습니다.');
            }
        });
    });
    const checkboxes = document.querySelectorAll('input[name="shFishList"]');
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
function updatePagination(thisPage, totalPages, paginationContainer,index) {
    // const paginationContainer = document.querySelector("#pagination-items .pagination");
    console.log()
    paginationContainer.innerHTML = ""; // 기존 페이지네이션 초기화

    const pageNumToShow = 5; // 보여줄 페이지 번호 수
    const startPage = Math.max(1, thisPage - Math.floor(pageNumToShow / 2));
    const endPage = Math.min(totalPages, startPage + pageNumToShow - 1);

    // 이전 페이지로 이동 버튼
    if (startPage > 1) {
        const prevButton = document.createElement("li");
        prevButton.className = "page-item";
        prevButton.innerHTML = `
            <a class="page-link" href="javascript:void(0)" onclick="goList${index}(${startPage - 1})">
                <i class="bi bi-caret-left-fill"></i>
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
    if (endPage < totalPages) {
        const nextButton = document.createElement("li");
        nextButton.className = "page-item";
        nextButton.innerHTML = `
            <a class="page-link" href="javascript:void(0)" onclick="goList${index}(${endPage + 1})">
                <i class="bi bi-caret-right-fill"></i>
            </a>`;
        paginationContainer.appendChild(nextButton);
    }
}
