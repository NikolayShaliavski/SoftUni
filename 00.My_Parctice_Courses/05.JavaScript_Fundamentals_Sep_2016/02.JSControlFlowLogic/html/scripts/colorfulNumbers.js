function colorfulNumbers() {
    "use strict";
    let num = Number(document.getElementById('number').value);

	let html = "<ul>";
    for (let i = 1; i <= num; i++) {
        let color = "blue";
        if (i % 2 == 0) {
            color = "green";
        }
	    html += `   <li><span style='color: ${color}'>${i}</span></li>\n`;
    }
    html += `</ul>\n`;
	// document.getElementById('output').innerHtml = html;
    document.write(html);
}
