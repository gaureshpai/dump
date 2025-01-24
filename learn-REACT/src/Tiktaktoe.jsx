import { useState } from 'react';
import './App.css'

function Square({ value, onSquareClick, className }) {
    return (
        <button className={`square ${className}`} onClick={onSquareClick}>
            {value}
        </button>
    );
}

function Board({ xIsNext, squares, onPlay }) {
    function handleClick(i) {
        if (calculateWinner(squares) || squares[i]) return;
        const nextSquares = squares.slice();
        nextSquares[i] = xIsNext ? 'X' : 'O';
        onPlay(nextSquares);
    }

    const result = calculateWinner(squares);
    let status;
    if (result?.winner) {
        status = 'Winner: ' + result.winner;
    } else if (result === 'Draw') {
        status = 'Draw';
    } else {
        status = 'Next player: ' + (xIsNext ? 'X' : 'O');
    }

    const board = [];
    for (let row = 0; row < 3; row++) {
        const boardRow = [];
        for (let col = 0; col < 3; col++) {
            const idx = row * 3 + col;
            const isWinningSquare = result?.line?.includes(idx);
            boardRow.push(
                <Square
                    key={idx}
                    value={squares[idx]}
                    onSquareClick={() => handleClick(idx)}
                    className={isWinningSquare ? 'winning-square' : ''}
                />
            );
        }
        board.push(
            <div key={row} className="board-row">
                {boardRow}
            </div>
        );
    }

    return (
        <>
            <div className="status">{status}</div>
            {board}
        </>
    );
}

export default function Game() {
    const [history, setHistory] = useState([Array(9).fill(null)]);
    const [currentMove, setCurrentMove] = useState(0);
    const [isAscending, setIsAscending] = useState(true);
    const xIsNext = currentMove % 2 === 0;
    const currentSquares = history[currentMove];

    function handlePlay(nextSquares) {
        const nextHistory = [...history.slice(0, currentMove + 1), nextSquares];
        setHistory(nextHistory);
        setCurrentMove(nextHistory.length - 1);
    }

    function jumpTo(nextMove) {
        setCurrentMove(nextMove);
    }

    const moves = history.map((squares, move) => {
        const lastMoveIdx = history[move - 1]?.findIndex((v, i) => v !== squares[i]);
        const row = Math.floor(lastMoveIdx / 3) + 1;
        const col = (lastMoveIdx % 3) + 1;
        const location = move > 0 ? `(${row}, ${col})` : '';

        let description;
        if (move === currentMove) {
            description = `You are at move #${move} ${location}`;
        } else if (move > 0) {
            description = `Go to move #${move} ${location}`;
        } else {
            description = 'Go to game start';
        }

        return (
            <li key={move}>
                {move === currentMove ? (
                    <span>{description}</span>
                ) : (
                    <button onClick={() => jumpTo(move)}>{description}</button>
                )}
            </li>
        );
    });

    const sortedMoves = isAscending ? moves : moves.slice().reverse();

    return (
        <div className="game">
            <div className="game-board">
                <Board xIsNext={xIsNext} squares={currentSquares} onPlay={handlePlay} />
            </div>
            <div className="game-info">
                <button onClick={() => setIsAscending(!isAscending)}>
                    {isAscending ? 'Sort Descending' : 'Sort Ascending'}
                </button>
                <ol>{sortedMoves}</ol>
            </div>
        </div>
    );
}

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6],
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return { winner: squares[a], line: [a, b, c] };
        }
    }
    return squares.every(Boolean) ? 'Draw' : null;
}
