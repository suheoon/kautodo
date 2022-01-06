import { API_BASE_URL } from "../app-config";

export function call(api, method, request) {

  let headers = new Headers({
      "Content-Type": "application/json",
  });

  // local storage에서 token가져오기
  const jwtToken = localStorage.getItem("JWT_TOKEN");
  if (jwtToken && jwtToken !== null) {
    headers.append("Authorization", "Bearer " + jwtToken);
  }

  let options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: method,
  };

  // Get
  if (request) {
    options.body = JSON.stringify(request);
  }
  
  return fetch(options.url, options)
    .then( async (res) => {
      if (res.status === 403) {
        window.location.href = "/login";
      }
      let data = await res.json();

      return data});
}

//로그인
export function signin(userDTO) {
  return call("/users/sign-in", "POST", userDTO).then((res) => {
    console.log(res);
    // 403 error (로그인이 되지 않은 경우)
    if (res.code === 3000) {
      alert('아이디 또는 비밀번호가 잘못 입력 되었습니다.\n' +
      '아이디와 비밀번호를 정확히 입력해주세요.')
    }
    else if (res.result.jwt) {
      // local storage에 jwt 토큰 저장
      localStorage.setItem("JWT_TOKEN", res.result.jwt);
      // token이 존재하는 경우 Todo 화면으로 리디렉트
      window.location.href = "/";
    } 
  });
}

//로그아웃
export function signout() {
  localStorage.setItem("JWT_TOKEN", null);
  window.location.href = "/login";
}

//회원가입
export function signup(userDTO) {
  return call("/users/sign-up", "POST", userDTO).then((res) => {
    if (res.code === 2001) {
      alert('이미 등록된 아이디 입니다.')
    } else {
      window.location.href="/login";
    }
  });
}