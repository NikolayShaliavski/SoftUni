function galacticElections(data) {
    let summary = {};
    for (let i of data) {
        let system = i['system'];
        let candidate = i['candidate'];
        let votes = Number(i['votes']);

        if (!summary.hasOwnProperty(system)) {
            summary[system] = {};
        }
        if (!summary[system].hasOwnProperty(candidate)) {
            summary[system][candidate] = 0;
        }
        summary[system][candidate] += votes;
    }

    //console.log("Before:");
    //console.log(summary);

    let allVotes = 0;
    let candidates = {};
    for (let key in summary) {
        let systemObj = summary[key];
        let winner = Object.keys(systemObj).reduce((a, b) => systemObj[a] >= systemObj[b] ? a : b);

        let systemVotes = 0;
        Object.keys(systemObj).forEach(function(key) {
            systemVotes += systemObj[key];
            systemObj[key] = 0;
        });

        systemObj[winner] = systemVotes;
        allVotes += systemVotes;
        if (!candidates.hasOwnProperty(winner)) {
            candidates[winner] = {votes: 0, systemsWon: []};
        }
        candidates[winner]['votes'] += systemVotes;
        candidates[winner]['systemsWon'].push({ system: key, votes: systemVotes});
    }

    let sortedCandidates = Object.keys(candidates).sort(function(a, b) {
        let votesA = candidates[a]['votes'];
        let votesB = candidates[b]['votes'];
        return votesB - votesA;
    });
    let sorted = {};
    let winnerName = sortedCandidates[0];
    let secondName = sortedCandidates[1];
    for(let key of sortedCandidates) {
        sorted[key] = candidates[key];
    }
    candidates = sorted;

    let firstCandidate = candidates[winnerName];
    let secondCandidate = candidates[secondName];
    let firstVotes = firstCandidate['votes'];
    if (firstVotes > allVotes / 2) {
        console.log(winnerName + " wins with " + firstVotes + " votes");
        if (secondCandidate != undefined) {
            console.log("Runner up: " + secondName);
            // let sortedKeys = Object.keys(secondCandidate['systemsWon']).sort(function(a, b) {
            //     let systemVotesA = 0;
            //     Object.keys(summary[a]).forEach(function(key) {
            //         systemVotesA += summary[a][key];
            //     });
            //     let systemVotesB = 0;
            //     Object.keys(summary[b]).forEach(function(key) {
            //         systemVotesB += summary[b][key];
            //     });
            //     return systemVotesB - systemVotesA;
            // });
            secondCandidate['systemsWon'].sort(function(a, b) {
                return b['votes'] - a['votes'];
            });
            for(let sys of secondCandidate['systemsWon']) {
                console.log(sys['system'] + ": " + sys['votes']);
            }
        } else {
            console.log(winnerName + " wins unopposed!");
        }
    } else {
        let firstPercent = Math.floor(firstVotes / allVotes * 100);
        let secondVotes = secondCandidate['votes'];
        let secondPercent = Math.floor(secondVotes / allVotes * 100);
        //Runoff between Space Cow with 43% and Flying Shrimp with 37%
        console.log("Runoff between " + winnerName + " with " + firstPercent + "% and " + secondName + " with " + secondPercent + "%");
    }
    //console.log(summary);
    // let sortedKeys = Object.keys(summary).sort(function(a, b) {
    //     let systemVotesA = 0;
    //     Object.keys(summary[a]).forEach(function(key) {
    //         systemVotesA += summary[a][key];
    //     });
    //     let systemVotesB = 0;
    //     Object.keys(summary[b]).forEach(function(key) {
    //         systemVotesB += summary[b][key];
    //     });
    //     return systemVotesB - systemVotesA;
    // });
    // let sorted = {};
    // for(let key of sortedKeys) {
    //     sorted[key] = summary[key];
    // }
    // summary = sorted;


    // console.log("Candidates: ");
    // console.log(candidates);
    // console.log("After:");
    // console.log(summary);
    //console.log("All votes: " + allVotes);
}

// galacticElections([ { system: 'Theta', candidate: 'Flying Shrimp', votes: 10 },
//   { system: 'Sigma', candidate: 'Space Cow',     votes: 200 },
//   { system: 'Sigma', candidate: 'Flying Shrimp', votes: 120 },
//   { system: 'Tau',   candidate: 'Space Cow',     votes: 15 },
//   { system: 'Sigma', candidate: 'Space Cow',     votes: 60 },
//   { system: 'Tau',   candidate: 'Flying Shrimp', votes: 150 } ]
// );

// galacticElections([ { system: 'Theta', candidate: 'Kim Jong Andromeda', votes: 10 },
//   { system: 'Tau',   candidate: 'Kim Jong Andromeda', votes: 200 },
//   { system: 'Tau',   candidate: 'Flying Shrimp',      votes: 150 } ]
// );

galacticElections([{ system: 'Theta', candidate: 'Octocat', votes: 10},{ system: 'Theta', candidate: 'Space Cow', votes: 10},{ system: 'Theta', candidate: 'Huge Manatee', votes: 10},{ system: 'Theta', candidate: 'Flying Shrimp', votes: 10},{ system: 'Theta', candidate: 'Octocat', votes: 1},{ system: 'Tau', candidate: 'Octocat', votes: 30},{ system: 'Tau', candidate: 'Space Cow', votes: 30},{ system: 'Tau', candidate: 'Huge Manatee', votes: 30},{ system: 'Tau', candidate: 'Flying Shrimp', votes: 30},{ system: 'Tau', candidate: 'Space Cow', votes: 1},{ system: 'Sigma', candidate: 'Octocat', votes: 10},{ system: 'Sigma', candidate: 'Space Cow', votes: 10},{ system: 'Sigma', candidate: 'Huge Manatee', votes: 10},{ system: 'Sigma', candidate: 'Flying Shrimp', votes: 10},{ system: 'Sigma', candidate: 'Huge Manatee', votes: 1},{ system: 'Omicron', candidate: 'Octocat', votes: 10},{ system: 'Omicron', candidate: 'Space Cow', votes: 10},{ system: 'Omicron', candidate: 'Huge Manatee', votes: 11},{ system: 'Omicron', candidate: 'Flying Shrimp', votes: 10},{ system: 'Omicron', candidate: 'Flying Shrimp', votes: 1},{ system: 'Omega', candidate: 'Huge Manatee', votes: 10},{ system: 'Theta', candidate: 'Octocat', votes: 1},{ system: 'Sigma', candidate: 'Huge Manatee', votes: 2},{ system: 'Tau', candidate: 'Octocat', votes: 10},{ system: 'Omega', candidate: 'Huge Manatee', votes: 10},{ system: 'Omega', candidate: 'Huge Manatee', votes: 10},{ system: 'Omega', candidate: 'Huge Manatee', votes: 10},{ system: 'Omega', candidate: 'Octocat', votes: 10}]);