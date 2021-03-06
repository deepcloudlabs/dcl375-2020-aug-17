/*
    CRM View Model
 */
class CrmViewModel {
    constructor() {
        // observables
        this.customer = new Customer();
        this.customers = ko.observableArray([]);
        this.fileData = ko.observable({
            dataUrl: ko.observable(AppConfig.NO_IMAGE)
        });
        this.x = ko.observable(2);
        this.y = ko.observable(3);
        this.z = ko.computed(()=>{
            return this.x() + this.y();
        })
        // bind methods to this
        this.findCustomerByIdentity = this.findCustomerByIdentity.bind(this);
        this.findAllCustomers = this.findAllCustomers.bind(this);
        this.addCustomer = this.addCustomer.bind(this);
        this.updateCustomer = this.updateCustomer.bind(this);
        this.removeCustomerByIdentity = this.removeCustomerByIdentity.bind(this);
        this.populatePhotoIfNull = this.populatePhotoIfNull.bind(this);
        this.copyCustomer = this.copyCustomer.bind(this);
        this.handleError = this.handleError.bind(this);
    }

    connectWebSocket = () => {
        this.socket = new SockJS("http://localhost:4001/crm/api/v1/changes");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.debug = () => {}
        this.stompClient.connect({}, (frame) => {
            toastr.success("WebSocket connection is ready!");
            this.stompClient.subscribe(
                "/topic/changes",
                (msg) => {
                    let payload = JSON.parse(msg.body);
                    payload.customer.photo= toSrcImage(payload.customer.photo);
                    this.customers.push(payload.customer);
                });
        });
    }

    changeLangToEn(){
        this.changeLang('en');
    }

    insertFile(e, data) {
        e.preventDefault();
        let files = e.target.files || e.originalEvent.dataTransfer.files;
        let reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = (event) => {
            this.fileData().dataUrl(event.target.result);
        };
    }

    dragover(e) {
        e.preventDefault();
    };

    changeLangToTr(){
        this.changeLang('tr');
    }
    i18n(){
       $(document).i18n();
    }
    changeLang(lang) {
        i18n.setLng(lang, ()=>{
            this.i18n();
            knockoutLocalize(lang);
            this.customer.validate();
        })
    }

    findCustomerByIdentity() {
        $.ajax({
            method: "GET",
            url: AppConfig.REST_API_BASE_URL
                + "/customers/" + this.customer.identity(),
            cache: false,
            success: (customer) => {
                this.populatePhotoIfNull(customer);
                this.fileData().dataUrl(customer.photo);
                this.customer.refresh(customer);
            },
            error: this.handleError
        })
    }

    copyCustomer(cust) {
        this.customer.refresh(cust);
        this.fileData().dataUrl(cust.photo);
    }

    removeCustomerByIdentity(cust) {
        let identity;
        if (cust.hasOwnProperty('customer'))
            identity = this.customer.identity();
        else {
            identity = cust.identity;
            this.customers.remove(cust);
        }

        $.ajax({
            method: "DELETE",
            url: AppConfig.REST_API_BASE_URL
                + "/customers/" + identity,
            success: (customer) => {
                this.populatePhotoIfNull(customer);
                this.fileData().dataUrl(customer.photo);
                this.customer.refresh(customer);
                ShowMessage(AppConfig.MESSAGE_TYPE.SUCCESS,
                    "Customer is deleted!");
            },
            error: this.handleError
        })
    }

    populatePhotoIfNull(customer) {
        if (!customer.hasOwnProperty('photo'))
            customer.photo = AppConfig.NO_IMAGE;
        else if (customer.photo == null)
            customer.photo = AppConfig.NO_IMAGE;
        if (customer.photo.substr(0, 22) != "data:image/png;base64,")
            customer.photo = toSrcImage(customer.photo);
    }

    addCustomer() {
        let data = ko.toJS(this.customer);
        data.photo = toRawImage(this.fileData().dataUrl());
        let json = JSON.stringify(data);
        $.ajax({
            method: "POST",
            url: AppConfig.REST_API_BASE_URL + "/customers",
            contentType: "application/json",
            data: json,
            success: (customer) => {
                ShowMessage(AppConfig.MESSAGE_TYPE.SUCCESS,
                    "Customer is added!");
            },
            error: this.handleError
        })
    }

    handleError(xhr) {
        let messages = JSON.parse(xhr.responseText).message.split('|');
        for (let i in messages)
            ShowMessage(AppConfig.MESSAGE_TYPE.ERROR, messages[i]);
    }

    updateCustomer() {
        let data = ko.toJS(this.customer);
        data.photo = toRawImage(this.fileData().dataUrl());
        let json = JSON.stringify(data);
        $.ajax({
            method: "PUT",
            url: AppConfig.REST_API_BASE_URL + "/customers",
            contentType: "application/json",
            data: json,
            success: (customer) => {
                ShowMessage(AppConfig.MESSAGE_TYPE.SUCCESS,
                    "Customer is updated!");
            },
            error: this.handleError
        })
    }

    findAllCustomers() {
        $.ajax({
            method: "GET",
            url: AppConfig.REST_API_BASE_URL
                + "/customers?page=0&size=10",
            cache: false,
            success: (customers) => {
                for (let i in customers) {
                    this.populatePhotoIfNull(customers[i]);
                }
                customers.sort(
                    (c1, c2) => c1.fullname
                        .localeCompare(c2.fullname)
                );
                this.customers(customers);
                this.i18n();
            },
            error: this.handleError
        })
    }
}