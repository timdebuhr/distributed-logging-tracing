import React from 'react';
import {Button, Checkbox, Grid, Header, Icon, Image, Input, Message} from 'semantic-ui-react';
import Container from "semantic-ui-react/dist/commonjs/elements/Container";
import Divider from "semantic-ui-react/dist/commonjs/elements/Divider";

class App extends React.Component {

    state = {
        backendData: ' ',
        lastGetData: ' ',
        lastAddedData: ' ',
        lastRemovedData: ' ',
        articleNumberToAdd: '',
        articleDescriptionToAdd: '',
        articleSubItemNumberToAdd: '',
        articleNumberToRemove: '',
        orderNumberToAdd: '',
        orderCustomerNumberToAdd: '',
        orderArticleNumberToAdd: '',
        orderNumberToRemove: '',
        customerNumberToAdd: '',
        customerNameToAdd: '',
        customerAddressToAdd: '',
        customerNumberToRemove: '',
        backendResponseSuccessful: false,
        managementView: false
    }

    fetchOrderList = () => {
        fetch('http://localhost:8093/workshop/api/orders', {headers: {"Content-Type": "application/json"}})
            .then(response => response.text())
            .then(text => this.setState({
                backendData: text,
                backendResponseSuccessful: true
            }));
    }

    fetchArticleList = () => {
        fetch('http://localhost:8090/workshop/api/items', {headers: {"Content-Type": "application/json"}})
            .then(response => response.text())
            .then(text => this.setState({
                backendData: text,
                backendResponseSuccessful: true
            }));
    }

    fetchCustomerList = () => {
        fetch('http://localhost:8091/workshop/api/customers', {headers: {"Content-Type": "application/json"}})
            .then(response => response.text())
            .then(text => this.setState({
                backendData: text,
                backendResponseSuccessful: true
            }));
    }

    fetchArticle = (articleNumber) => {
        return fetch('http://localhost:8090/workshop/api/items/' + articleNumber, {
            headers: {"Content-Type": "application/json"}
        }).then(response => {
            if (response.status === 404) {
                return new Promise((resolve) => {
                    this.setState({
                        lastGetData: '{"message":"Nothing found for article number: ' + articleNumber + '"}',
                        backendResponseSuccessful: false
                    });
                    resolve();
                });
            } else {
                return response.text().then(text => this.setState({
                    lastGetData: text,
                    backendResponseSuccessful: true
                }));
            }
        });
    }

    fetchOrder = (orderNumber) => {
        return fetch('http://localhost:8093/workshop/api/orders/' + orderNumber, {
            headers: {"Content-Type": "application/json"}
        }).then(response => {
            if (response.status === 404) {
                return new Promise((resolve) => {
                    this.setState({
                        lastGetData: '{"message":"Nothing found for order number: ' + orderNumber + '"}',
                        backendResponseSuccessful: false
                    });
                    resolve();
                });
            } else {
                return response.text().then(text => this.setState({
                    lastGetData: text,
                    backendResponseSuccessful: true
                }));
            }
        });
    }

    fetchCustomer = (customerNumber) => {
        return fetch('http://localhost:8091/workshop/api/customers/' + customerNumber, {
            headers: {"Content-Type": "application/json"}
        }).then(response => {
            if (response.status === 404) {
                return new Promise((resolve) => {
                    this.setState({
                        lastGetData: '{"message":"Nothing found for customer number: ' + customerNumber + '"}',
                        backendResponseSuccessful: false
                    });
                    resolve();
                });
            } else {
                return response.text().then(text => this.setState({
                    lastGetData: text,
                    backendResponseSuccessful: true
                }));
            }
        });
    }

    fetchAddArticle = () => {
        const subItems = this.state.articleSubItemNumberToAdd
            .split(',')
            .map(subArticleNumber => {
                let subItem = {};
                subItem['itemNumber'] = subArticleNumber;
                return subItem;
            });
        const article = {
            itemNumber: this.state.articleNumberToAdd,
            itemDescription: this.state.articleDescriptionToAdd,
            subItems: [...subItems]
        };
        console.log("create", article);
        fetch('http://localhost:8090/workshop/api/items', {
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
            },
            method: 'POST',
            body: JSON.stringify(article)
        })
            .then(response => this.handleResponse(response))
            .then(text => this.setState({
                lastAddedData: text,
                articleNumberToAdd: '',
                articleDescriptionToAdd: '',
                articleSubItemNumberToAdd: '',
                lastRemovedData: ' '
            }));
    }

    fetchAddOrder = () => {
        const items = this.state.orderArticleNumberToAdd
            .split(',')
            .map(articleNumber => {
                let item = {};
                item['itemNumber'] = articleNumber;
                return item;
            });
        const order = {
            orderNumber: this.state.orderNumberToAdd,
            customer: {
                customerNumber: this.state.orderCustomerNumberToAdd
            },
            items: [...items]
        }
        console.log("create", order);
        fetch('http://localhost:8093/workshop/api/orders', {
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            method: 'POST',
            body: JSON.stringify(order)
        })
            .then(response => this.handleResponse(response))
            .then(text => this.setState({
                lastAddedData: text,
                orderNumberToAdd: '',
                orderCustomerNumberToAdd: '',
                orderArticleNumberToAdd: '',
                lastRemovedData: ' '
            }))
    }

    fetchAddCustomer = () => {
        const customer = {
            customerNumber: this.state.customerNumberToAdd,
            customerName: this.state.customerNameToAdd,
            address: {
                addressNumber: this.state.customerAddressToAdd
            }
        }
        console.log("create", customer);
        fetch('http://localhost:8091/workshop/api/customers', {
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            method: 'POST',
            body: JSON.stringify(customer)
        })
            .then(response => this.handleResponse(response))
            .then(text => this.setState({
                lastAddedData: text,
                customerNumberToAdd: '',
                customerNameToAdd: '',
                customerAddressToAdd: '',
                lastRemovedData: ' '
            }))
    }

    fetchRemoveArticle = () => {
        this.fetchArticle(this.state.articleNumberToRemove).then(value => {
            console.log("Response from get:", value);
            fetch('http://localhost:8090/workshop/api/items/' + this.state.articleNumberToRemove, {
                mode: "cors",
                headers: {"Content-Type": "application/json"},
                method: 'DELETE'
            }).then(() => this.setState({
                lastRemovedData: this.state.lastGetData,
                articleNumberToRemove: '',
                lastAddedData: ' '
            }));
        });
    }

    fetchRemoveOrder = () => {
        this.fetchOrder(this.state.orderNumberToRemove).then(value => {
            console.log("Response from get:", value);
            fetch('http://localhost:8093/workshop/api/orders/' + this.state.orderNumberToRemove, {
                mode: "cors",
                headers: {"Content-Type": "application/json"},
                method: 'DELETE'
            }).then(() => this.setState({
                lastRemovedData: this.state.lastGetData,
                orderNumberToRemove: '',
                lastAddedData: ' '
            }));
        });
    }

    fetchRemoveCustomer = () => {
        this.fetchCustomer(this.state.customerNumberToRemove).then(value => {
            console.log("Response from get:", value);
            fetch('http://localhost:8091/workshop/api/customers/' + this.state.customerNumberToRemove, {
                mode: "cors",
                headers: {"Content-Type": "application/json"},
                method: 'DELETE'
            }).then(() => this.setState({
                lastRemovedData: this.state.lastGetData,
                customerNumberToRemove: '',
                lastAddedData: ' '
            }));
        });
    }

    clearBackendData = () => {
        this.setState({
            backendData: ' ',
            lastGetData: ' ',
            lastAddedData: ' ',
            lastRemovedData: ' ',
            articleNumberToAdd: '',
            articleDescriptionToAdd: '',
            articleSubItemNumberToAdd: '',
            articleNumberToRemove: '',
            orderNumberToAdd: '',
            orderCustomerNumberToAdd: '',
            orderArticleNumberToAdd: '',
            orderNumberToRemove: '',
            customerNumberToAdd: '',
            customerNameToAdd: '',
            customerAddressToAdd: '',
            customerNumberToRemove: '',
            backendResponseSuccessful: false
        });
    }

    handleResponse(response) {
        if (!response.ok) {
            return new Promise((resolve) => {
                this.setState({
                    backendResponseSuccessful: false
                });
                resolve(response.text());
            });
        } else {
            return new Promise((resolve) => {
                this.setState({
                    backendResponseSuccessful: true
                });
                resolve(response.text());
            });
        }
    }

    switchView = () => {
        this.setState({managementView: !this.state.managementView})
    }

    convert(text) {
        return (!!text && text.trim().length > 0) ? JSON.stringify(JSON.parse(text), null, 2) : '';
    }

    render() {
        return (
            <Container style={{border: '1px #006491 solid', height: '100%'}}>
                <Grid style={{margin: '0', height: '100%'}}>
                    <Grid.Row style={{
                        padding: '0',
                        height: '101px',
                        backgroundColor: 'lightgrey',
                        borderBottom: '1px solid #006491'
                    }}>
                        <Grid.Column width={3} style={{display: 'flex', alignItems: 'center'}}>
                            <Image src='logo_new.png' size='small' wrapped style={{margin: 'auto'}}/>
                        </Grid.Column>
                        <Grid.Column width={10} style={{display: 'flex', alignItems: 'center'}}>
                            <Header as='h2' icon textAlign='center' style={{margin: 'auto', color: '#006491'}}>
                                Simple Webshop
                                <Header.Subheader style={{color: '#006491'}}>A simple Webshop for Backendworkshops
                                </Header.Subheader>
                            </Header>
                        </Grid.Column>
                        <Grid.Column width={3} style={{display: 'flex', alignItems: 'center'}}>
                            <Grid.Row style={{marginLeft: 'auto'}}>
                                <Grid.Column style={{height: '50%', display: 'flex', alignItems: 'center'}}>
                                    <Button onClick={this.clearBackendData}>Clear</Button>
                                </Grid.Column>
                            </Grid.Row>
                        </Grid.Column>
                    </Grid.Row>
                    <Grid.Row style={{backgroundColor: '#f8f8f9', height: '45px'}}>
                        <Grid.Column width={12} style={{display: 'flex', alignItems: 'center'}}>
                            <Divider horizontal style={{margin: '0'}}>
                                <Header as='h4'>Interact with Backend REST API</Header>
                            </Divider>
                        </Grid.Column>
                        <Grid.Column width={4} style={{display: 'flex', alignItems: 'center'}}>
                            <Checkbox
                                style={{marginLeft: 'auto', marginRight: '14px', marginTop: '0', marginBottom: 'auto'}}
                                checked={this.state.managementView} label='Management View'
                                onChange={this.switchView} toggle/>
                        </Grid.Column>
                    </Grid.Row>
                    <Grid.Row style={{backgroundColor: '#f8f8f9', height: '36px', padding: '0'}}>
                        <Grid.Column style={{display: 'flex', alignItems: 'center'}}>
                            {(() => {
                                if (!this.state.managementView) {
                                    return (<>
                                        <Button style={{marginLeft: 'auto', marginRight: '5px'}}
                                                onClick={this.fetchOrderList}>List Orders</Button>
                                        <Button style={{marginLeft: '5px', marginRight: '5px'}}
                                                onClick={this.fetchArticleList}>List Articles</Button>
                                        <Button style={{marginLeft: '5px', marginRight: 'auto'}}
                                                onClick={this.fetchCustomerList}>List Customers</Button>
                                    </>);
                                }
                            })()}

                        </Grid.Column>
                    </Grid.Row>
                    <Grid.Row style={{
                        backgroundColor: '#f8f8f9',
                        height: 'calc(100% - 36px - 45px - 101px - 30px)',
                        overflow: 'auto'
                    }}>
                        {(() => {
                            if (!this.state.managementView) {
                                return (
                                    <Grid.Column width={10} style={{
                                        display: 'flex',
                                        alignItems: 'center',
                                        marginLeft: 'auto',
                                        marginRight: 'auto',
                                        height: '100%'
                                    }}>
                                        <Message style={{
                                            width: '100%',
                                            height: '100%',
                                            backgroundColor: 'white',
                                            boxShadow: '0 0 0 1px rgba(0,0,0,.6) inset,0 0 0 0 transparent',
                                            overflow: 'auto'
                                        }}>
                                            <pre style={{
                                                color: 'rgba(0,0,0,.8)',
                                                display: 'inline-block'
                                            }}>{this.convert(this.state.backendData)}</pre>
                                        </Message>
                                    </Grid.Column>)
                            } else {
                                return (
                                    <Grid.Column width={10} textAlign='center' style={{
                                        display: 'flex',
                                        flexDirection: 'column',
                                        marginLeft: 'auto',
                                        marginRight: 'auto',
                                        height: '100%'
                                    }}>
                                        <Grid
                                            style={{width: '100%', margin: 'auto', marginTop: '0', marginBottom: '0'}}>
                                            <Grid.Row style={{padding: '0'}}><strong>Add Order:</strong></Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Order number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}><Input style={{width: '100%'}}
                                                          value={this.state.orderNumberToAdd}
                                                          onChange={(event) => {
                                                              this.setState({orderNumberToAdd: event.target.value})
                                                          }}></Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Customer number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.orderCustomerNumberToAdd}
                                                           onChange={(event) => {
                                                               this.setState({orderCustomerNumberToAdd: event.target.value})
                                                           }}>
                                                    </Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Article number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.orderArticleNumberToAdd}
                                                           onChange={(event) => {
                                                               this.setState({orderArticleNumberToAdd: event.target.value})
                                                           }}>
                                                    </Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Button style={{marginLeft: 'auto', marginRight: '0'}}
                                                        disabled={this.state.orderNumberToAdd === ''}
                                                        onClick={this.fetchAddOrder}>Add Order</Button>
                                            </Grid.Row>
                                        </Grid>
                                        <Grid
                                            style={{width: '100%', margin: 'auto', marginTop: '0', marginBottom: '0'}}>
                                            <Grid.Row style={{padding: '0'}}><strong>Add Customer:</strong></Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Customer number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}><Input style={{width: '100%'}}
                                                          value={this.state.customerNumberToAdd}
                                                          onChange={(event) => {
                                                              this.setState({customerNumberToAdd: event.target.value})
                                                          }}></Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Customer name:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.customerNameToAdd}
                                                           onChange={(event) => {
                                                               this.setState({customerNameToAdd: event.target.value})
                                                           }}>
                                                    </Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Address number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.customerAddressToAdd}
                                                           onChange={(event) => {
                                                               this.setState({customerAddressToAdd: event.target.value})
                                                           }}>
                                                    </Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Button style={{marginLeft: 'auto', marginRight: '0'}}
                                                        disabled={this.state.customerNumberToAdd === ''}
                                                        onClick={this.fetchAddCustomer}>Add Customer</Button>
                                            </Grid.Row>
                                        </Grid>
                                        <Grid
                                            style={{width: '100%', margin: 'auto', marginTop: '0', marginBottom: '30px'}}>
                                            <Grid.Row style={{padding: '0'}}><strong>Add Article:</strong></Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Article number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}><Input style={{width: '100%'}}
                                                          value={this.state.articleNumberToAdd}
                                                          onChange={(event) => {
                                                              this.setState({articleNumberToAdd: event.target.value})
                                                          }}></Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Article description:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.articleDescriptionToAdd}
                                                           onChange={(event) => {
                                                               this.setState({articleDescriptionToAdd: event.target.value})
                                                           }}>
                                                    </Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Sub article number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.articleSubItemNumberToAdd}
                                                           onChange={(event) => {
                                                               this.setState({articleSubItemNumberToAdd: event.target.value})
                                                           }}>
                                                    </Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Button style={{marginLeft: 'auto', marginRight: '0'}}
                                                        disabled={this.state.articleNumberToAdd === ''}
                                                        onClick={this.fetchAddArticle}>Add Article</Button>
                                            </Grid.Row>
                                            <Grid.Row style={{
                                                display: this.state.lastAddedData.trim().length > 0 ? 'block' : 'none',
                                                height: '150px'
                                            }}>
                                                <Message style={{
                                                    width: '100%',
                                                    height: '100%',
                                                    backgroundColor: this.state.backendResponseSuccessful ? 'lightgreen' : 'lightcoral',
                                                    boxShadow: '0 0 0 1px rgba(0,0,0,.6) inset,0 0 0 0 transparent',
                                                    textAlign: 'left',
                                                    overflow: 'auto'
                                                }}>
                                                    <pre style={{
                                                        color: 'rgba(0,0,0,.8)',
                                                        display: 'inline-block'
                                                    }}>{this.convert(this.state.lastAddedData)}</pre>
                                                </Message>
                                                {this.state.backendResponseSuccessful
                                                    ? 'Adding processed successfully!' : 'Problem during processing adding'}
                                            </Grid.Row>
                                            <Grid.Row style={{
                                                display: this.state.lastRemovedData.trim().length > 0 ? 'block' : 'none',
                                                height: '150px'
                                            }}>
                                                <Message style={{
                                                    width: '100%',
                                                    height: '100%',
                                                    backgroundColor: this.state.backendResponseSuccessful ? 'lightgreen' : 'lightcoral',
                                                    boxShadow: '0 0 0 1px rgba(0,0,0,.6) inset,0 0 0 0 transparent',
                                                    textAlign: 'left',
                                                    overflow: 'auto'
                                                }}>
                                                    <pre style={{
                                                        color: 'rgba(0,0,0,.8)',
                                                        display: 'inline-block'
                                                    }}>{this.convert(this.state.lastRemovedData)}</pre>
                                                </Message>
                                                {this.state.backendResponseSuccessful
                                                    ? 'Removing processed successfully!' : 'Problem during processing removing'}
                                            </Grid.Row>
                                        </Grid>
                                        <Grid style={{width: '100%', margin: 'auto', marginTop: '0', marginBottom: '0'}}>
                                            <Grid.Row style={{padding: '0'}}><strong>Remove Order:</strong></Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Order number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.orderNumberToRemove}
                                                           onChange={(event) => {
                                                               this.setState({orderNumberToRemove: event.target.value})
                                                           }}></Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Button style={{marginLeft: 'auto', marginRight: '0'}}
                                                        disabled={this.state.orderNumberToRemove === ''}
                                                        onClick={this.fetchRemoveOrder}>Remove Order</Button>
                                            </Grid.Row>
                                        </Grid>
                                        <Grid style={{width: '100%', margin: 'auto', marginTop: '0', marginBottom: '0'}}>
                                            <Grid.Row style={{padding: '0'}}><strong>Remove Customer:</strong></Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Customer number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.customerNumberToRemove}
                                                           onChange={(event) => {
                                                               this.setState({customerNumberToRemove: event.target.value})
                                                           }}></Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Button style={{marginLeft: 'auto', marginRight: '0'}}
                                                        disabled={this.state.customerNumberToRemove === ''}
                                                        onClick={this.fetchRemoveCustomer}>Remove Customer</Button>
                                            </Grid.Row>
                                        </Grid>
                                        <Grid style={{width: '100%', margin: 'auto', marginTop: '0', marginBottom: '30px'}}>
                                            <Grid.Row style={{padding: '0'}}><strong>Remove Article:</strong></Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Grid.Column style={{
                                                    textAlign: 'left',
                                                    width: '20%',
                                                    margin: 'auto',
                                                    padding: '0'
                                                }}>Article number:
                                                </Grid.Column>
                                                <Grid.Column style={{
                                                    width: '80%',
                                                    padding: '0'
                                                }}>
                                                    <Input style={{width: '100%'}}
                                                           value={this.state.articleNumberToRemove}
                                                           onChange={(event) => {
                                                               this.setState({articleNumberToRemove: event.target.value})
                                                           }}></Input>
                                                </Grid.Column>
                                            </Grid.Row>
                                            <Grid.Row style={{padding: '0'}}>
                                                <Button style={{marginLeft: 'auto', marginRight: '0'}}
                                                        disabled={this.state.articleNumberToRemove === ''}
                                                        onClick={this.fetchRemoveArticle}>Remove Article</Button>
                                            </Grid.Row>
                                        </Grid>
                                    </Grid.Column>)
                            }
                        })()}
                    </Grid.Row>
                    <Grid.Row
                        style={{backgroundColor: '#006491', height: '30px', paddingTop: '5px', paddingBottom: '5px'}}>
                        <Grid.Column style={{display: 'flex', alignItems: 'center'}}>
                            <a style={{
                                width: '20%',
                                color: 'lightgrey',
                                textDecoration: 'none'
                            }} href='mailto:info@tdbit.de'><Icon name='mail'/>Kontakt</a>
                            <a style={{
                                width: '60%',
                                textAlign: 'center',
                                color: 'lightgrey',
                                textDecoration: 'none'
                            }} href='https://www.tdbit.de' rel='noopener noreferrer'>TDBIT - Consulting</a>
                            <div style={{
                                width: '20%'
                            }}></div>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Container>
        );
    }
}

export default App;
