INSERT INTO monster (name, description, hp, img_path) VALUES
    ('WARN', '잠재적인 위험을 안고 있는 상태인 몬스터', 50, 'images/monster/warn.gif'),
    ('ERROR', '오류가 발생했지만, 실행하는 것에는 문제가 없는 몬스터', 100, 'images/monster/error.gif'),
    ('FATAL', '애플리케이션을 중지해야 할 심각한 오류를 가진 몬스터', 200, 'images/monster/fatal.gif'),
    ('404', '요청한 몬스터의 정보를 찾을 수 없습니다.', 1000, 'images/monster/404.gif');

INSERT INTO item_type (name) VALUES
     ('소모품'),
     ('장비'),
     ('퀘스트'),
     ('기타');

INSERT INTO item (name, description, img_path, item_type_id) VALUES
     ('초콜릿', '기분이 좋아지는 달달한 초콜릿이다.', 'images/item/food/chocolate.png', 1),
     ('햄버거', '방금 만든 따뜻한 햄버거이다.', 'images/item/food/hamburger.png', 1),
     ('피자', '치즈가 쭈욱 늘어나는 맛있는 피자다.', 'images/item/food/pizza.png', 1),
     ('콜라', '톡쏘는 시원함을 주는 콜라다.', 'images/item/food/coke.png', 1),
     ('커피', '마음이 따뜻해지는 뜨거운 커피다.', 'images/item/food/coffee.png', 1),
     ('에너지드링크', '정신이 번쩍 드는 에너지드링크다.', 'images/item/food/energy-drink.png', 1),
     ('문방구 키보드', '조금만 만져도 부서질 것 같은 키보드다.', 'images/item/weapon/Keyboard_1.png', 2),
     ('기계식 키보드', '깔끔한 디자인을 가진 기계식 키보드다.' , 'images/item/weapon/Keyboard_2.png', 2),
     ('게이밍 키보드', '좋은 반응속도를 가진 게이밍 키보드다.', 'images/item/weapon/Keyboard_3.png', 2),
     ('문방구 마우스', '금방이라도 부서질 것 같은 마우스다.', 'images/item/weapon/Mouse_1.png', 2),
     ('게이밍 마우스', '좋은 반응속도를 가진 게이밍 마우스다.', 'images/item/weapon/Mouse_2.png', 2),
     ('한정판 마우스', '커스텀 특별제작으로 만들어진 마우스다.', 'images/item/weapon/Mouse_3.png', 2),
     ('3G 피처폰', 'LTE 사용이 불가능한 구형 3G 피처폰이다.', 'images/item/weapon/Phone_1.png', 2),
     ('보급형 스마트폰', '버벅임이 심한 보급형 스마트폰이다.', 'images/item/weapon/Phone_2.png', 2),
     ('플래그쉽 스마트폰', '빠르고 강력한 성능을 가진 스마트폰이다.', 'images/item/weapon/Phone_3.png', 2);