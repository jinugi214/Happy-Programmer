module.exports = {
    // 빌드시 사용하지 않는 CSS 제거
    purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],

    // 기본 테마. 색상, 폰트, 여백, 그림자 등. colors 속성 사용하여 기본 색상 값 설정/변경하기
    theme: {
        extend: {
          colors: {
            navy: "#002060",
            pink: "#F4C4DA",
            purple: "#CEBFF4",
            blue: "#7995B9",
            sky: "#B5DCFC",
            
            // button
            navybold: "#0E0327",

            // background
            bgone: "#A7A7A7",
            bgtwo: "#9397BD",
            bgbox: "#2D346F",
          },

          fontSize: {
            s: "1.2rem",
            m: "1.6rem",
          },

        },
      },
    
    // 사용자 정의 플러그인 설정
    plugins: [],
  };
  