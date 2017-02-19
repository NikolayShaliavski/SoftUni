function colorfulNumbers(input) {
    "use strict";
    let num = Number(input[0]);

    let html = `<ul>\n`;
    for (let i = 1; i <= num; i++) {
        let color = "blue";
        if (i % 2 != 0) {
            color = "green";
        }
        html += `   <li><span style='color: ${color}'>${i}</span></li>\n`;
    }
    html += `</ul>\n`;
    return html;
}

document.body.innerHTML = colorfulNumbers(['10']);
