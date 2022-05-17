import React, { useEffect, useState } from 'react';
import { Graphviz } from 'graphviz-react'

import api from './environments/api'
import './styles.scss'

function App() {

    const a = "Algo"
    const [sql, setSql] = useState("");
    const [algebra, setAlgebra] = useState("");
    const [selection, setSelection] = useState();

    async function handleCreateAlgebra(event) {
        event.preventDefault();
        const body = {
            query: sql.toUpperCase()
        }

        try {
            const data = await api.post('/query', body)
            setAlgebra(data.data)
            setSelection(data.data.bSelection)
            console.log(data.data);
           // alert(selection)
        } catch (error) {
            //alert(error)
        }
    }

    return (
        <div id="page-home">
            <header>
                <div className="container">
                    <h1>Relational Algebra</h1>
                </div>

            </header>
            <main id="main">
                <form onSubmit={handleCreateAlgebra}>
                    <p>Input Query</p>
                    <textarea
                        required="required"
                        onChange={event => setSql(event.target.value)}
                        value={sql}
                    />
                    <button>Submit</button>
                </form>

            </main>
            <div>
                <strong>Algebra</strong>
                <p id='p'>
                    {algebra.algebra}
                </p>

            </div>
            
            <div>
                <strong>Grafo</strong>
                <Graphviz dot={`digraph {
                    a -> b
                    b -> c
                    c -> a
                  }`} />

                  <></>

            </div>

        </div>
    )
}


export default App;