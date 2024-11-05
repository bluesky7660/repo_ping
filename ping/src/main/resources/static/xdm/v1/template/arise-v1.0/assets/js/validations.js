

(() => {
	"use strict";

	const forms = document.querySelectorAll('.needs-validation');

	Array.from(forms).forEach((form) => {
		form.addEventListener('submit', (event) => {
			// const validateElements = form.querySelectorAll('.validate-this');
			const validateElements = form.querySelectorAll('.form-control:required:not(:read-only), .form-select');

			let allValid = true;

			validateElements.forEach((element) => {
				// 유효성 검사를 제외할 클래스를 지정
                const excludeValidationClass = 'exclude-validation';	

                // 요소에 유효성 검사 제외 클래스가 있는지 확인
                if (element.classList.contains(excludeValidationClass)) {
                    return; // 유효성 검사를 수행하지 않음
                }

				
				if (!element.checkValidity()) {
					allValid = false;
					element.classList.add('is-invalid');
				} else {
					element.classList.remove('is-invalid');
					element.classList.add('is-valid');
				}
			});

			if (!allValid) {
				event.preventDefault();	// 페이지전환막기
				event.stopPropagation();
			} else {

				form.classList.add('was-validated');
			}
		}, false);
	});
})();