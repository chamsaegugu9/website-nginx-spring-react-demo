import React, { useEffect } from 'react';
import ReactDOM from 'react-dom/client';

import { BrowserRouter, createBrowserRouter, Navigate, Outlet, RouterProvider, useLocation, } from 'react-router-dom';
import Main from './router/Main';
import Layout from './router/Layout';
import Login from './router/Login';
import HelloBeerListLayout from './router/HelloBeerListLayout';
import HelloBeerDetailLayout from './router/HelloBeerDetailLayout';


const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      ...["/", "main"].map((n) => {
        return { path: n, element: <Main /> };
      }),
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "beer",
        children: [
          {
            path: "list",
            element: <HelloBeerListLayout />,
          },
          {
            path: "detail/:id",
            element: <HelloBeerDetailLayout />,
          }
        ]
      },
      {
        path: "about",
        element: (<div></div>),
      },
      {
        path: "project",
        element: (<div></div>),
      },

    ],
  }
]);

function App(props) {
  
  return (
    <RouterProvider router={router} />
  );
}

export default App;