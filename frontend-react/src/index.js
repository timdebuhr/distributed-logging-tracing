import React from 'react';
import ReactDOM from 'react-dom/client';
import 'semantic-ui-css/semantic.min.css'
import App from './App';
import { TraceProvider } from "./tracing";

ReactDOM.createRoot(document.getElementById('root')).render(
    <TraceProvider>
        <App />
    </TraceProvider>
);

