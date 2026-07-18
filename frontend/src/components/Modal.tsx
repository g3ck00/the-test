interface ModalProps {
    isOpen: boolean;
    title: string;
    onClose: () => void;
    children: React.ReactNode;
}

export default function Modal({
                                  isOpen,
                                  title,
                                  onClose,
                                  children,
                              }: ModalProps) {
    if (!isOpen) return null;

    return (
        <div
            style={{
                position: "fixed",
                inset: 0,
                backgroundColor: "rgba(15, 23, 42, 0.55)",
                backdropFilter: "blur(4px)",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                zIndex: 9999,
                padding: "20px",
            }}
            onClick={onClose}
        >
            <div
                style={{
                    width: "100%",
                    maxWidth: "650px",
                    maxHeight: "90vh",
                    overflowY: "auto",
                    background: "#ffffff",
                    borderRadius: "20px",
                    boxShadow: "0 25px 50px rgba(0,0,0,0.25)",
                    padding: "24px",
                    position: "relative",
                }}
                onClick={(e) => e.stopPropagation()}
            >
                <div
                    style={{
                        display: "flex",
                        justifyContent: "space-between",
                        alignItems: "center",
                        marginBottom: "20px",
                    }}
                >
                    <h2
                        style={{
                            margin: 0,
                            fontSize: "1.4rem",
                            color: "#1e293b",
                        }}
                    >
                        {title}
                    </h2>

                    <button
                        type="button"
                        onClick={onClose}
                        style={{
                            border: "none",
                            background: "#f1f5f9",
                            width: "36px",
                            height: "36px",
                            borderRadius: "50%",
                            cursor: "pointer",
                            fontSize: "1.2rem",
                            color: "#475569",
                        }}
                    >
                        ×
                    </button>
                </div>

                {children}
            </div>
        </div>
    );
}