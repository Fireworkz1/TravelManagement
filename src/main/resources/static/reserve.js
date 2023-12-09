// scripts.js

// 获取数据容器和进度条
var dataContainer = document.getElementById('dataContainer');
var progress = document.getElementById('progress');

// 当进度条变化时触发的事件
progress.addEventListener('input', function () {
    // 计算当前应该显示的数据索引
    var index = Math.floor((progress.value / 100) * dataContainer.children.length);

    // 更新数据容器的滚动位置
    dataContainer.scrollTop = dataContainer.children[index].offsetTop;
});
