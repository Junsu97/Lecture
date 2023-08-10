// HTML 요소들을 선택합니다.
const container = document.querySelector('.container');
const search = document.querySelector('.search-box button');
const weatherBox = document.querySelector('.weather-box');
const weatherDetails = document.querySelector('.weather-details');
const error404 = document.querySelector('.not-found');

// 검색 버튼 클릭 시 실행될 함수를 정의합니다.
search.addEventListener('click', () => {

    // OpenWeatherMap API 키와 검색할 도시를 가져옵니다.
    const APIKey = 'dcd5a0fe977515706a46c2eb5476784b';
    const city = document.querySelector('.search-box input').value;

    // 입력된 도시가 비어있는 경우 알림을 띄우고 함수를 종료합니다.
    if (city === '') {
        alert('도시 이름을 입력하세요.');
        return;
    }

    // OpenWeatherMap API를 호출하여 날씨 정보를 가져옵니다.
    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${APIKey}`)
        .then(response => response.json())
        .then(json => {

            // 검색 결과가 없는 경우 화면 요소들을 조정하고 알림을 표시합니다.
            if (json.cod === '404') {
                alert('해당 도시를 찾을 수 없습니다.');
                container.style.height = '400px';
                weatherBox.style.display = 'none';
                weatherDetails.style.display = 'none';
                error404.style.display = 'block';
                error404.classList.add('fadeIn');
                return;
            }

            // 검색 결과가 있는 경우 화면 요소들을 업데이트합니다.
            error404.style.display = 'none';
            error404.classList.remove('fadeIn');

            // 화면 요소들을 선택합니다.
            const image = document.querySelector('.weather-box img');
            const temperature = document.querySelector('.weather-box .temperature');
            const description = document.querySelector('.weather-box .description');
            const humidity = document.querySelector('.weather-details .humidity span');
            const wind = document.querySelector('.weather-details .wind span');

            // 날씨 상태에 따라 이미지를 업데이트합니다.
            switch (json.weather[0].main) {
                case 'Clear':
                    image.src = '/img/clear.png';
                    break;

                case 'Rain':
                    image.src = '/img/rain.png';
                    break;

                case 'Snow':
                    image.src = '/img/snow.png';
                    break;

                case 'Clouds':
                    image.src = '/img/cloud.png';
                    break;

                case 'Haze':
                    image.src = '/img/mist.png';
                    break;

                default:
                    image.src = '';
            }

            // 날씨 정보를 화면에 표시합니다.
            temperature.innerHTML = `${parseInt(json.main.temp)}<span>°C</span>`;
            description.innerHTML = `${json.weather[0].description}`;
            humidity.innerHTML = `${json.main.humidity}%`;
            wind.innerHTML = `${parseInt(json.wind.speed)}Km/h`;

            // 화면 요소들의 애니메이션을 적용하고 크기를 조정합니다.
            weatherBox.style.display = '';
            weatherDetails.style.display = '';
            weatherBox.classList.add('fadeIn');
            weatherDetails.classList.add('fadeIn');
            container.style.height = '590px';

        });

});
