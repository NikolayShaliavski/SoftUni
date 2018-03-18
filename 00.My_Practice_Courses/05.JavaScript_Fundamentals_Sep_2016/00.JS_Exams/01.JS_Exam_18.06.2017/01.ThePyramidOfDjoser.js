function pyramidOfDjoser(base, height) {
    //let base = Number(data[0]);
    //let height = Number(data[1]);

    let stone = 0;
    let marble = 0;
    let lapis = 0;
    let gold = 0;
    let step = 1;
    while(true) {
        if (base <= 2) {
            gold += base * base;
            break;
        }
        let blocksInRow = base * base;
        let fill = base - 2;
        let innerLayer = fill * fill;
        stone += innerLayer;

        if (step % 5 === 0) {
            lapis += blocksInRow - innerLayer;
        } else {
            marble += blocksInRow - innerLayer;
        }
        step++;
        base -= 2;
    }

    stone = Math.ceil(stone * height);
    marble = Math.ceil(marble * height);
    lapis = Math.ceil(lapis * height);
    gold = Math.ceil(gold * height);
    step = Math.floor(step * height);

    console.log("Stone required: " + stone);
    console.log("Marble required: " + marble);
    console.log("Lapis Lazuli required: " + lapis);
    console.log("Gold required: " + gold);
    console.log("Final pyramid height: " + step);
}

pyramidOfDjoser(11, 0.75);