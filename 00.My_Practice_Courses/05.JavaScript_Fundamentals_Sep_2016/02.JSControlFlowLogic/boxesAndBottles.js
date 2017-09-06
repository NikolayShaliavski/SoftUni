function calculateBoxes(input) {
    var bottles = parseInt(input[0]);
    var capacity = parseInt(input[1]);

    var boxes = Math.floor(bottles / capacity);
    if (bottles % capacity != 0) {
        boxes++;
    }
    console.log(boxes);
}

calculateBoxes(['15', '7']);
