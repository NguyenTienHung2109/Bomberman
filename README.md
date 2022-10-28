# Bomberman - Bài tập lớn OOP
![markdown](https://cdn.wallpapersafari.com/15/68/K5NVof.png)

***
## Nhóm 10
#### Nguyễn Tiến Hùng - K66CB
#### Phạm Hoàng - k66CD
#### Nguyễn Thị Lan Nhi - K66CC
## Nội dung  
[Mô tả chung](#mô-tả-chung)  
  - [Các đối tượng](#các-đối-tượng)  
  - [Mô tả game play](#mô-tả-game-play)
  - [Các chức năng](#các-chức-năng)
  
[Các kỹ thuật được sử dụng](#các-kỹ-thuật-được-sử-dụng)

[Tổng kết](#tổng-kết)
  - [Hướng phát triển](#hướng-phát-triển)
  - [Điều tâm đắc](#điều-tâm-đắc)
## Mô tả chung
Nếu bạn đã từng chơi Bomberman, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (Bomber, Enemy, Bomb) và nhóm đối tượng tĩnh (Grass, Wall, Brick, Door, Item).
### Các đối tượng
- ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/player/player_down.png?raw=true) **Bomber** là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.
- ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/balloom/balloom_left1.png?raw=true) **Enemy** là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/bomb/bomb.png?raw=true) **Bomb** là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng Flame  được tạo ra.
- ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/map/grass.png?raw=true) **Grass** là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó.
- ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/map/wall.png?raw=true) **Wall** là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này.
- ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/map/portal.png?raw=true) **Portal** là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.
- Các **Item** cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
  - ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/map/powerup_speed.png?raw=true) **SpeedItem** Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp.
  - ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/map/powerup_flames.png?raw=true) **FlameItem** Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn).
  - ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/map/powerup_bombs.png?raw=true) **BombItem** Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.
Có nhiều loại Enemy trong Bomberman, tuy nhiên trong phiên bản này chỉ yêu cầu cài đặt hai loại Enemy dưới đây (nếu cài đặt thêm các loại khác sẽ được cộng thêm điểm):
  - ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/enemy/balloom/balloom_left1.png?raw=true) **Balloom** là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
  - ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/enemy/oneal/oneal_left1.png?raw=true) **Oneal** có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm và di chuyển "thông minh" hơn so với Balloom (biết đuổi theo Bomber)
  - ![markdown](https://github.com/NguyenTienHung2109/Bomberman/blob/main/res/enemy/kondoria/kondoria_left1.png?raw=true) **Kondora** là một enemy mạnh với khả năng xuyên tường và đuổi theo Bomber.
### Mô tả game play
- Trong một màn chơi, **Bomber** sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí **Portal** để có thể qua màn mới.
- **Bomber** sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi **Bomb** nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ.
- Một đối tượng thuộc phạm vi **Bomb** nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng **Bomb** nổ.
- Khi **Bomb** nổ, một Flame trung tâm tại vị trí **Bomb** nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của **Bomb** xuất hiện theo bốn hướng trên/dưới/trái/phải. Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng **Bomb** đó cũng sẽ nổ ngay lập tức.
### Các chức năng
- Menu hiển thị các trạng thái chơi
- Người chơi di chuyển **Bomber** bằng các phím W: lên, S: xuống, A: sang trái, D: sang phải. Khi người chơi thu được các item sẽ nhận một sức mạnh tương ứng với item đó trong một khoảng thời gian ngắn.
- Chức năng dừng game khi đang chơi.
- Âm thanh khi chơi game, đặt bom, va chạm.
- Tính điểm và thời gian chơi.
## Các kỹ thuật được sử dụng
## Tổng kết
### Hướng phát triển
- Thêm một số tính năng như:
    - Thêm các **Enemy** với nhiều khả năng khác nhau ví dụ như: nhả item, tránh bom, nhảy qua tường, tàng hình, nhả bom, nhiều mạng ...
    - Bảng xếp hạng
    - Bảng option chọn level chơi, điều chỉnh âm thanh, góc nhìn...
    - Nhiều người cùng chơi
    - Thêm nhiều nhân vật chơi với các khả năng khác nhau như bắn **Enemy**, chạy nhanh, nhiều mạng...
### Điều tâm đắc
- Sau một thời gian làm việc nhóm cùng nhau chúng em đã học hỏi thêm được rất nhiều kiến thức và tư duy lập trình OOP cùng nhiều những kiến thức bổ ích khác. Ngoài ra, chúng em cũng học được các kĩ năng khi làm việc nhóm, kĩ năng tìm kiếm thông tin,...Chúng em cảm thấy rất vui và biết ơn các thầy đã hướng dẫn và chỉ bảo chúng em để chúng em có thể hoàn thành bài tập lớn này.
