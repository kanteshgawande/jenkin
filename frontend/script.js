const API = "http://localhost:8081/game";

async function loadGame() {
    const res = await fetch(API);
    const data = await res.json();

    updateBoard(data.board);
    document.getElementById("status").innerText =
        data.gameOver ? "Game Over" : data.player + " turn";
}

async function makeMove(i) {
    await fetch(`${API}/move/${i}`, { method: "POST" });
    loadGame();
}

async function resetGame() {
    await fetch(`${API}/reset`, { method: "POST" });
    loadGame();
}

function updateBoard(board) {
    const cells = document.querySelectorAll(".cell");
    cells.forEach((cell, i) => {
        cell.innerText = board[i] || "";
    });
}

window.onload = loadGame;